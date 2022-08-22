package com.demo.metrorail.service.business.stationdetails;

import java.util.List;

import com.demo.metrorail.dto.StationFootfallResponse;
import com.demo.metrorail.entity.Stations;

public interface StationDetailsService {

	/**
	 * <p>
	 * This method should return the station details for entered stations
	 * </p>
	 * <br>
	 * @param station_in, station_out : Input stations
	 * @return {@link Stations} List of stations {Stations}
	 */

	public List<Stations> getStationDetails(String station_in, String station_out);

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
	
	/**
	 * <p>
	 * This method should save the footfall details of all stations .
	 * </p>
	 * <br>
	 * @param {List of @link Stations} 
	 *
	 */
	public void saveAllStationDetails(List<Stations> stationDetails);
	
	
}
