package com.demo.metrorail.service.business.station;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.data.service.StationDetailsDataService;
import com.demo.metrorail.dto.StationFootfallResponse;

import lombok.Data;

@Service
@Data
public class StationDetailsImpl implements StationDetails {

	@Autowired
	private StationDetailsDataService stationDetailsDataService;

	@Override
	public List<StationFootfallResponse> getAllStationsFootfall() {
		return this.stationDetailsDataService.getAllStationsFootfall();
	}
}
