package com.demo.metrorail.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.repository.CardDetailsRepository;

@Service
public class CardDetailsDataService {

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	/**
	 * @param cardNumber
	 * @return BankAccount if account exists, else returns null
	 */
	public MetroCard getAccountDetailsForCardNumber(String cardNumber) {
		Optional<MetroCard> cardHolderDetails = this.cardDetailsRepository.getAccountDetailsForCardNumber(cardNumber);
		if (cardHolderDetails.isEmpty()) {
			throw new CardNumberNotFoundException("Account not exists for card number " + cardNumber);
		}
		return cardHolderDetails.get();
	}

	public List<MetroCard> getAllCardDetails() {
		List<MetroCard> cardDetails = new ArrayList<>();
		this.cardDetailsRepository.findAll().forEach(ba -> {
			ba.setCard_pin(null);
			cardDetails.add(ba);
		});
		return cardDetails;
	}

}
