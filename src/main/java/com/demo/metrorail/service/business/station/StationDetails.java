package com.demo.metrorail.service.business.station;

import java.util.List;

import com.demo.metrorail.dto.StationFootfallResponse;

public interface StationDetails {

	/**
	 * <p>
	 * This method should return the footfall details of all stations as list of
	 * StationFootfallResponse.
	 * </p>
	 * <br>
	 * 
	 * @return {@link StationFootfallResponse} A footfall enquiry response for all
	 *         stations {@StationFootfallResponse}
	 */

	public List<StationFootfallResponse> getAllStationsFootfall();
}
