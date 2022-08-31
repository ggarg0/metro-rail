package com.demo.metrorail.service.business.carddetails;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.constant.MessageConstants;
import com.demo.metrorail.data.service.CardDetailsDataService;
import com.demo.metrorail.dto.BalanceInquiry;
import com.demo.metrorail.dto.BalanceInquiryResponse;
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
	 * @param balanceInquiry {@link BalanceInquiry} details for getting account
	 *                       information.
	 * @return
	 */
	public BalanceInquiryResponse getCardBalanceForUser(BalanceInquiry balanceInquiry) {
		if (Objects.isNull(balanceInquiry)) {
			return new BalanceInquiryResponse(null, null, null, null, 0l, MessageConstants.InvalidBalanceInquiry);
		}

		// Fetch card details from DB.
		MetroCard metroCardDetails = null;
		try {
			metroCardDetails = getAccountDetailsForCardNumber(balanceInquiry.getCardNumber());

		} catch (CardNumberNotFoundException exp) {
			return new BalanceInquiryResponse(null, null, null, balanceInquiry.getCardNumber(), 0l,
					MessageConstants.CardNumberNotFound);
		}

		// Authenticate
		if (this.authenticationService.authenticateCardHolderAccount(metroCardDetails, balanceInquiry.getPin())) {
			// Respond Balance.
			return new BalanceInquiryResponse(metroCardDetails.getUser_name(), metroCardDetails.getFirst_name(),
					metroCardDetails.getLast_name(), metroCardDetails.getCard_number(), metroCardDetails.getBalance(),
					"");

		} else {
			return new BalanceInquiryResponse(null, null, null, balanceInquiry.getCardNumber(), 0l,
					MessageConstants.InvalidPin);
		}

	}

	@Override
	public List<MetroCard> getAllCardDetails() {
		return this.cardDetailsDataService.getAllCardDetails();
	}

	@Override
	public void saveAllCardDetails(List<MetroCard> cardDetails) {
		this.cardDetailsDataService.saveAllCardDetails(cardDetails);
	}

	@Override
	public MetroCard getAccountDetailsForCardNumber(String cardNumber) {
		return this.cardDetailsDataService.getAccountDetailsForCardNumber(cardNumber);
	}

}
