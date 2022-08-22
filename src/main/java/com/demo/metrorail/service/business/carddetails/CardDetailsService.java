package com.demo.metrorail.service.business.carddetails;

import java.util.List;

import com.demo.metrorail.dto.BalanceEnquiry;
import com.demo.metrorail.dto.BalanceEnquiryResponse;
import com.demo.metrorail.entity.MetroCard;

public interface CardDetailsService {
	/**
	 * <p>
	 * This method should return the balance details of the card holder as
	 * BalanceEnquiryResponse.
	 * </p>
	 * <br>
	 * This method should check the card PIN from the balanceEnquiry request and
	 * authenticate against the user pin <br>
	 * On successful authentication this method should provide the details of
	 * card holder <br>
	 * else should respond with the BalanceEnquiryResponse as card pin not correct.
	 *
	 * @param balanceEnquiry {@link BalanceEnquiry} details for getting card
	 *                       information.
	 * @return {@link BalanceEnquiryResponse} A balance enquiry response for the
	 *         requested {@BalanceEnquiry}
	 */
	BalanceEnquiryResponse getCardBalanceForUser(BalanceEnquiry balanceEnquiry);
	
	 /**
     * This method should return the details of all card holder.
     * <br> This is more for the audit purpose.
     *
     * @return
     */
    List<MetroCard> getAllCardDetails();

	
}
