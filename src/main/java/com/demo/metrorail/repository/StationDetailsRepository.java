package com.demo.metrorail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.metrorail.entity.Stations;

@Repository
public interface StationDetailsRepository extends CrudRepository<Stations, Integer> {

}
