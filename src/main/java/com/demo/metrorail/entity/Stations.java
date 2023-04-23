package com.demo.metrorail.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "STATIONS")
@AllArgsConstructor
@NoArgsConstructor
public class Stations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String station_name;
	private Integer station_sequence;
	private Integer in_count;
	private Integer out_count;
	private Long tariff;

}
