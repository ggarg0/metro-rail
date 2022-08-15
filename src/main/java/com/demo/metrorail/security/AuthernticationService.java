package com.demo.metrorail.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.metrorail.entity.MetroCard;

@Service
public class AuthernticationService {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	public Boolean authenticateBankAccount(MetroCard metroCard, String pinPlainText) {
		return this.passwordEncoder().matches(pinPlainText, metroCard.getCard_pin());
	}

}
