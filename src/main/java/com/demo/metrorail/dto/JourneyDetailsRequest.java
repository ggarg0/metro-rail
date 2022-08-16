package com.demo.metrorail.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneyDetailsRequest {
	@Pattern(regexp = "^[0-9]{5}", message = "Card number can only be number and that to 5 digits only")
	@NotBlank(message = "Card number cannot be blank")
	private String cardNumber;

	@Pattern(regexp = "^[0-9]{4}", message = "Pin can only be number and that to 4 digits only")
	@NotBlank(message = "PIN cannot be blank")
	private String pin;

	@Pattern(regexp = "^[A-Z]{1}[0-9]{1}", message = "Station can have 2 digits with letter followed by a number")
	@NotBlank(message = "Station cannot be blank")
	private String stationIn;

	@Pattern(regexp = "^[A-Z]{1}[0-9]{1}", message = "Station can have 2 digits with letter followed by a number")
	@NotBlank(message = "Station cannot be blank")
	private String stationOut;

	private String message;

}
