package com.demo.metrorail.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.metrorail.dto.BalanceEnquiry;
import com.demo.metrorail.dto.BalanceEnquiryResponse;
import com.demo.metrorail.dto.StationFootfallResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.service.business.carddetails.CardDetails;
import com.demo.metrorail.service.business.station.StationDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/metro/api")
public class MetroRailController {

	@Autowired
	CardDetails cardDetails;

	@Autowired
	StationDetails stationDetails;
	
	/**
	 * <p>
	 * This controller method will authenticate the pin for user, On successful
	 * validation, The requested users balance amount will be returned.
	 * </p>
	 *
	 * @param balanceEnquiry {@link com.demo.metrorail.dto.BalanceEnquiry} A custom
	 *                       balance enquiry object.
	 * @return {@link com.demo.metrorail.dto.BalanceEnquiryResponse}. Metro card
	 *         details for user.
	 */

	@Operation(summary = "Get the card balance for user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Will respond with card details for user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = BalanceEnquiryResponse.class)) }) })
	@PostMapping("balance")
	public BalanceEnquiryResponse getCardBalanceForUser(@Valid @RequestBody BalanceEnquiry balanceEnquiry) {
		return this.cardDetails.getCardBalanceForUser(balanceEnquiry);
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
	 * This controller method will fetch the denomination details from ATM. <br>
	 * This is more of a audit feature. No authentication is added here.
	 *
	 * @return {@link com.abcbank.data.entity.DenominationDetail} A list of
	 *         Denomination Details.
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
