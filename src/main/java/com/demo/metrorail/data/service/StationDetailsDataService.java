package com.demo.metrorail.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.constant.MessageConstants;
import com.demo.metrorail.dto.StationFootfallResponse;
import com.demo.metrorail.entity.Stations;
import com.demo.metrorail.exceptions.StationDetailsNotFoundException;
import com.demo.metrorail.repository.StationDetailsRepository;

@Service
public class StationDetailsDataService {

	@Autowired
	private StationDetailsRepository stationDetailsRepository;

	public List<StationFootfallResponse> getAllStationsFootfall() {
		List<StationFootfallResponse> staionsDetails = new ArrayList<>();

		try {
			this.stationDetailsRepository.findAll().forEach(station -> {
				staionsDetails.add(new StationFootfallResponse(station.getStation_name(), station.getIn_count(),
						station.getOut_count(), ""));
			});

			if (staionsDetails.isEmpty()) {
				throw new StationDetailsNotFoundException(MessageConstants.StationDetailsNotFound);
			}
		} catch (StationDetailsNotFoundException exp) {
			staionsDetails.add(new StationFootfallResponse(null, 0, 0, MessageConstants.StationDetailsNotFound));
		}

		return staionsDetails;
	}

	public List<Stations> getStationDetails(String station_in, String station_out) {
		List<Stations> stationDetails = new ArrayList<>();
		stationDetails = this.stationDetailsRepository.getStationDetails(station_in, station_out);

		if (stationDetails.size() != 2) {
			throw new StationDetailsNotFoundException(MessageConstants.StationDetailsNotFound);
		}

		return stationDetails;
	}

	public void saveAllStationDetails(List<Stations> stationDetails) {
		this.stationDetailsRepository.saveAll(stationDetails);
	}

}
