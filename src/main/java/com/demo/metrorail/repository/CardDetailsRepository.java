package com.demo.metrorail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.metrorail.entity.MetroCard;

@Repository
public interface CardDetailsRepository extends CrudRepository<MetroCard, Integer> {

	@Query("SELECT ba FROM MetroCard ba WHERE ba.card_number = :cardNumber")
	Optional<MetroCard> getAccountDetailsForCardNumber(@Param("cardNumber") String cardNumber);

}
