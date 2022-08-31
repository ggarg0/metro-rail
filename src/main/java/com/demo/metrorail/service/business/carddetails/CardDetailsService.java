package com.demo.metrorail.service.business.carddetails;

import java.util.List;

import com.demo.metrorail.dto.BalanceInquiry;
import com.demo.metrorail.dto.BalanceInquiryResponse;
import com.demo.metrorail.entity.MetroCard;

public interface CardDetailsService {
	/**
	 * <p>
	 * This method should return the balance details of the card holder as
	 * BalanceInquiryResponse.
	 * </p>
	 * <br>
	 * This method should check the card PIN from the balanceInquiry request and
	 * authenticate against the user pin <br>
	 * On successful authentication this method should provide the details of card
	 * holder <br>
	 * else should respond with the balanceInquiryResponse as card pin not correct.
	 *
	 * @param balanceInquiry {@link BalanceInquiry} details for getting card
	 *                       information.
	 * @return {@link BalanceInquiryResponse} A balance inquiry response for the
	 *         requested {@BalanceInquiry}
	 */
	BalanceInquiryResponse getCardBalanceForUser(BalanceInquiry balanceInquiry);

	/**
	 * This method should return the details of all card holder. <br>
	 * This is more for the audit purpose.
	 *
	 * @param CardNumber {String}
	 * @return {@link MetroCard} A card details object for the requested CardNumber
	 */
	MetroCard getAccountDetailsForCardNumber(String cardNumber);

	/**
	 * This method should return the details of all card holder. <br>
	 * This is more for the audit purpose.
	 *
	 * @return
	 */
	List<MetroCard> getAllCardDetails();

	/**
	 * <p>
	 * This method should save the carddetails details.
	 * </p>
	 * <br>
	 * 
	 * @param {List of @link MetroCard}
	 *
	 */
	void saveAllCardDetails(List<MetroCard> cardDetails);
}
