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
@Table(name = "METRO_CARD")
@AllArgsConstructor
@NoArgsConstructor
public class MetroCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String email;
	private String card_number;
	private String card_pin;
	private Long balance;

}
