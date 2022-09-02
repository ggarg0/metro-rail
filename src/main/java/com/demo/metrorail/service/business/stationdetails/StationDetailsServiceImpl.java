package com.demo.metrorail.service.business.stationdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.data.service.StationDetailsDataService;
import com.demo.metrorail.dto.StationFootfallResponse;
import com.demo.metrorail.entity.Stations;

@Service
public class StationDetailsServiceImpl implements StationDetailsService {

	@Autowired
	private StationDetailsDataService stationDetailsDataService;

	@Override
	public List<Stations> getStationDetails(String station_in, String station_out) {
		return this.stationDetailsDataService.getStationDetails(station_in, station_out);
	}
	
	@Override
	public List<StationFootfallResponse> getAllStationsFootfall() {
		return this.stationDetailsDataService.getAllStationsFootfall();
	}
	
	@Override
	public void saveAllStationDetails(List<Stations> stationDetails) {
		this.stationDetailsDataService.saveAllStationDetails(stationDetails);
	}
}
