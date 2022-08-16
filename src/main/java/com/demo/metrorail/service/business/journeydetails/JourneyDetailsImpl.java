package com.demo.metrorail.service.business.journeydetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.constant.MessageConstants;
import com.demo.metrorail.data.service.CardDetailsDataService;
import com.demo.metrorail.data.service.StationDetailsDataService;
import com.demo.metrorail.dto.BalanceEnquiry;
import com.demo.metrorail.dto.JourneyDetailsRequest;
import com.demo.metrorail.dto.JourneyDetailsResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.entity.Stations;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.exceptions.InsufficientFundsException;
import com.demo.metrorail.exceptions.StationDetailsNotFoundException;
import com.demo.metrorail.security.AuthernticationService;

import lombok.Data;

@Service
@Data
public class JourneyDetailsImpl implements JourneyDetails {

	@Autowired
	AuthernticationService authenticationService;

	@Autowired
	private CardDetailsDataService cardDetailsDataService;

	@Autowired
	private StationDetailsDataService stationDetailsDataService;
	
	 private final Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * {@inheritDoc}
	 *
	 * @param balanceEnquiry {@link BalanceEnquiry} details for getting account
	 *                       information.
	 * @return
	 */

	@Transactional
	@Override
	public synchronized JourneyDetailsResponse getJourneyDetailsForUser(JourneyDetailsRequest journeyDetailsRequest) {
		if (Objects.isNull(journeyDetailsRequest)) {
			return new JourneyDetailsResponse(null, null, null, journeyDetailsRequest.getCardNumber(), 0l,
					MessageConstants.InvalidJourneyEnquiry);
		}

		MetroCard metroCardDetails = null;
		long remainingBalance = 0l;
		List<MetroCard> metroDetails = new ArrayList<MetroCard>();
		List<Stations> stationDetails = new ArrayList<Stations>();

		try {			
			logger.info("Fetching card details from database");
			metroCardDetails = this.cardDetailsDataService
					.getAccountDetailsForCardNumber(journeyDetailsRequest.getCardNumber());

			logger.info("Fetching station details from database");
			stationDetails = this.stationDetailsDataService.getStationDetails(journeyDetailsRequest.getStationIn(),
					journeyDetailsRequest.getStationOut());

			// Authenticate
			if (this.authenticationService.authenticateCardHolderAccount(metroCardDetails,
					journeyDetailsRequest.getPin())) {
				long fare = calculateFareAndUpdateSwipeCount(stationDetails, journeyDetailsRequest.getStationIn(),
						journeyDetailsRequest.getStationOut());

				remainingBalance = metroCardDetails.getBalance() - fare;

				if (remainingBalance < 0) {
					throw new InsufficientFundsException(MessageConstants.InsufficientAmountInAccountMessage);
				}

				metroCardDetails.setBalance(remainingBalance);
				metroDetails.add(metroCardDetails);
				this.cardDetailsDataService.saveAllCardDetails(metroDetails);
				this.stationDetailsDataService.saveAllStationDetails(stationDetails);

				// Respond Balance.
				return new JourneyDetailsResponse(metroCardDetails.getUser_name(), metroCardDetails.getFirst_name(),
						metroCardDetails.getLast_name(), metroCardDetails.getCard_number(),
						metroCardDetails.getBalance(), "Total fare from " + journeyDetailsRequest.getStationIn()
								+ " to " + journeyDetailsRequest.getStationOut() + " is " + fare);
			} else {
				logger.error(MessageConstants.InvalidPin);
				return new JourneyDetailsResponse(null, null, null, journeyDetailsRequest.getCardNumber(), 0l,
						MessageConstants.InvalidPin);
			}
		} catch (CardNumberNotFoundException cardException) {
			logger.error(MessageConstants.CardNumberNotFound);
			return new JourneyDetailsResponse(journeyDetailsRequest.getCardNumber(), null, null, null, 0l,
					MessageConstants.CardNumberNotFound);
		} catch (StationDetailsNotFoundException stationException) {
			logger.error(MessageConstants.StationDetailsNotFound);
			return new JourneyDetailsResponse(journeyDetailsRequest.getCardNumber(), metroCardDetails.getUser_name(),
					metroCardDetails.getFirst_name(), metroCardDetails.getLast_name(), metroCardDetails.getBalance(),
					MessageConstants.StationDetailsNotFound);
		} catch (InsufficientFundsException fundException) {
			logger.error(MessageConstants.InsufficientAmountInAccountMessage);
			return new JourneyDetailsResponse(journeyDetailsRequest.getCardNumber(), metroCardDetails.getUser_name(),
					metroCardDetails.getFirst_name(), metroCardDetails.getLast_name(), metroCardDetails.getBalance(),
					MessageConstants.InsufficientAmountInAccountMessage);
		}
	}

	public long calculateFareAndUpdateSwipeCount(List<Stations> stationDetails, String station_in, String station_out) {
		long inAmount = 0;
		long outAmount = 0;
		for (Stations station : stationDetails) {
			if (station_in.equalsIgnoreCase(station.getStation_name())) {
				inAmount = station.getTariff();
				station.setIn_count(station.getIn_count() + 1);
			}

			if (station_out.equalsIgnoreCase(station.getStation_name())) {
				outAmount = station.getTariff();
				station.setOut_count(station.getOut_count() + 1);
			}
		}
		return (inAmount > outAmount ? inAmount - outAmount : outAmount - inAmount);
	}
}
