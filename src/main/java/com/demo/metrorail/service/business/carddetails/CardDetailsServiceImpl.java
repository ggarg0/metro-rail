package com.demo.metrorail.service.business.carddetails;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.constant.MessageConstants;
import com.demo.metrorail.data.service.CardDetailsDataService;
import com.demo.metrorail.dto.BalanceEnquiry;
import com.demo.metrorail.dto.BalanceEnquiryResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.security.AuthenticationService;

import lombok.Data;

@Service
@Data
public class CardDetailsServiceImpl implements CardDetailsService {

	@Autowired
	private CardDetailsDataService cardDetailsDataService;

	@Autowired
	AuthenticationService authenticationService;

	/**
	 * {@inheritDoc}
	 *
	 * @param balanceEnquiry {@link BalanceEnquiry} details for getting account
	 *                       information.
	 * @return
	 */
	public BalanceEnquiryResponse getCardBalanceForUser(BalanceEnquiry balanceEnquiry) {
		if (Objects.isNull(balanceEnquiry)) {
			return new BalanceEnquiryResponse(null, null, null, null, 0l,
					MessageConstants.InvalidBalanceEnquiry);
		}

		// Fetch card details from DB.
		MetroCard metroCardDetails = null;
		try {
			metroCardDetails = this.cardDetailsDataService
					.getAccountDetailsForCardNumber(balanceEnquiry.getCardNumber());
		} catch (CardNumberNotFoundException exp) {
			return new BalanceEnquiryResponse(null, null, null, balanceEnquiry.getCardNumber(), 0l,
					MessageConstants.CardNumberNotFound);
		}

		// Authenticate
		if (this.authenticationService.authenticateCardHolderAccount(metroCardDetails, balanceEnquiry.getPin())) {
			// Respond Balance.
			return new BalanceEnquiryResponse(metroCardDetails.getUser_name(), metroCardDetails.getFirst_name(),
					metroCardDetails.getLast_name(), metroCardDetails.getCard_number(), metroCardDetails.getBalance(),
					"");

		} else {
			return new BalanceEnquiryResponse(null, null, null, balanceEnquiry.getCardNumber(), 0l,
					MessageConstants.InvalidPin);
		}

	}

	@Override
	public List<MetroCard> getAllCardDetails() {
		return this.cardDetailsDataService.getAllCardDetails();
	}

}
