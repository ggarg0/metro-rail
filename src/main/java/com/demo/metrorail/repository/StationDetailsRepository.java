package com.demo.metrorail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.metrorail.entity.Stations;

@Repository
public interface StationDetailsRepository extends CrudRepository<Stations, Integer> {

	@Query("SELECT st FROM Stations st WHERE st.station_name IN (:station_in,:station_out)")
	List<Stations> getStationDetails(@Param("station_in") String station_in,
			@Param("station_out") String station_out);

}
