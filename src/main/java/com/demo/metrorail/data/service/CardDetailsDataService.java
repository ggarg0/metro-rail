package com.demo.metrorail.data.service;

import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.repository.CardDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardDetailsDataService {

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    /**
     * @param cardNumber
     * @return Card holder if account exists, else returns null
     */
    public MetroCard getAccountDetailsForCardNumber(String cardNumber) {
        return this.cardDetailsRepository.getAccountDetailsForCardNumber(cardNumber)
                .orElseThrow(() -> new CardNumberNotFoundException("Card number not found" + " : " + cardNumber));
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
