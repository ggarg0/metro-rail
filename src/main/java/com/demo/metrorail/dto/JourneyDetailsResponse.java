package com.demo.metrorail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JourneyDetailsResponse {
	private String cardNumber;
	private String userName;
	private String firstName;
	private String lastName;
	private Long balance;
	private String message;
}
