package com.demo.metrorail.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.repository.CardDetailsRepository;

@Service
public class CardDetailsDataService {

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	/**
	 * @param cardNumber
	 * @return Card holder if account exists, else returns null
	 */
	public MetroCard getAccountDetailsForCardNumber(String cardNumber) {
		MetroCard cardHolderDetails = this.cardDetailsRepository.getAccountDetailsForCardNumber(cardNumber);
		return cardHolderDetails;
	}

	public List<MetroCard> getAllCardDetails() {
		List<MetroCard> cardDetails = new ArrayList<>();
		this.cardDetailsRepository.findAll().forEach(ba -> {
			ba.setCard_pin(null);
			cardDetails.add(ba);
		});
		return cardDetails;
	}

	public void saveAllCardDetails(List<MetroCard> cardDetails) {
		this.cardDetailsRepository.saveAll(cardDetails);
	}

}
