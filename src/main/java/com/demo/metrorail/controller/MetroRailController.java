package com.demo.metrorail.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.metrorail.dto.BalanceInquiry;
import com.demo.metrorail.dto.BalanceInquiryResponse;
import com.demo.metrorail.dto.JourneyDetailsRequest;
import com.demo.metrorail.dto.JourneyDetailsResponse;
import com.demo.metrorail.dto.StationFootfallResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.service.business.carddetails.CardDetailsService;
import com.demo.metrorail.service.business.journeydetails.JourneyDetailsService;
import com.demo.metrorail.service.business.stationdetails.StationDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/metro/api")
public class MetroRailController {

	@Autowired
	CardDetailsService cardDetails;

	@Autowired
	StationDetailsService stationDetails;

	@Autowired
	JourneyDetailsService journeyDetails;

	/**
	 * <p>
	 * This controller method will authenticate the pin for user, On successful
	 * validation, The requested users balance amount will be returned.
	 * </p>
	 *
	 * @param balanceInquiry {@link com.demo.metrorail.dto.BalanceInquiry} A custom
	 *                       balance inquiry object.
	 * @return {@link com.demo.metrorail.dto.BalanceInquiryResponse}. Metro card
	 *         details for user.
	 */

	@Operation(summary = "Get the card balance for user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Will respond with card details for user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = BalanceInquiryResponse.class)) }) })
	@PostMapping("balance")
	public BalanceInquiryResponse getCardBalanceForUser(@Valid @RequestBody BalanceInquiry balanceInquiry) {
		return this.cardDetails.getCardBalanceForUser(balanceInquiry);
	}

	/**
	 * <p>
	 * This controller method will authenticate the pin for user, On successful
	 * validation, tariff for the journey will be calculated. 1) In case of
	 * sufficient balance, journey details with the updated balance would be
	 * returned 2) In case of insufficient balance, insufficient balance message
	 * would be returned
	 * </p>
	 *
	 * @param balanceInquiry {@link com.demo.metrorail.dto.JourneyDetailsRequest} A
	 *                       custom journey details object.
	 * @return {@link com.demo.metrorail.dto.JourneyDetailsResponse}. Journey
	 *         details response for user.
	 */

	@Operation(summary = "Book a ticket and get journey details for user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Will respond with tariff details for journey", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = JourneyDetailsResponse.class)) }) })
	@PostMapping("journey-details")
	public JourneyDetailsResponse getJourneyDetailsForUser(
			@Valid @RequestBody JourneyDetailsRequest journeyDetailsRequest) {
		return this.journeyDetails.getJourneyDetailsForUser(journeyDetailsRequest);
	}

	/**
	 * This controller method will fetch the details of all card holders. <br>
	 * This is more of a audit feature. No authentication is added here.
	 *
	 * @return {@link com.demo.metrorail.entity.MetroCard} A list of card holder
	 *         details.
	 */

	@Operation(summary = "Get all the card holder details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Will respond with the card holder details", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MetroCard.class)) }) })
	@GetMapping("cardholder-inventory")
	public List<MetroCard> getAllCardDetails() {
		return this.cardDetails.getAllCardDetails();
	}

	/**
	 * This controller method will fetch the footfall details of all stations. <br>
	 * This is more of a audit feature. No authentication is added here.
	 *
	 * @return {@link com.demo.metrorail.dto.StationFootfallResponse} A list of
	 *         station Footfall Details.
	 */
	@Operation(summary = "Get all stations footfall count")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Will respond with  footfall count of all stations", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = StationFootfallResponse.class)) }) })
	@GetMapping("station-footfall")
	public List<StationFootfallResponse> getAllStationsFootfall() {
		return this.stationDetails.getAllStationsFootfall();
	}

}
