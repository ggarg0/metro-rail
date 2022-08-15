package com.demo.metrorail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationFootfallResponse {

	private String stationName;
	private int inCount;
	private int outCount;
	private String message;
}
