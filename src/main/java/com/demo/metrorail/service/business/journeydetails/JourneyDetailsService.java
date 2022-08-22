package com.demo.metrorail.service.business.journeydetails;

import com.demo.metrorail.dto.JourneyDetailsRequest;
import com.demo.metrorail.dto.JourneyDetailsResponse;

public interface JourneyDetailsService {
	/**
	 * <p>
	 * This method should return the journey details of the card holder as
	 * JourneyDetailsResponse.
	 * </p>
	 * <br>
	 * This method should check the card PIN from the JourneyDetailsRequest request
	 * and authenticate against the user pin <br>
	 * On successful authentication this method should provide the journey details
	 * of card holder <br>
	 * else should respond with the JourneyDetailsResponse as card pin not correct.
	 *
	 * @param JourneyDetailsRequest {@link JourneyDetailsRequest} details for getting journey
	 *                       information.
	 * @return {@link JourneyDetailsResponse} A journey details response for the
	 *         requested {JourneyDetailsRequest}
	 */
	JourneyDetailsResponse getJourneyDetailsForUser(JourneyDetailsRequest journeyDetailsRequest);

}
