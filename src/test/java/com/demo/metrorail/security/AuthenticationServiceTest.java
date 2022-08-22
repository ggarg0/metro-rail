package com.demo.metrorail.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.metrorail.entity.MetroCard;

@SpringBootTest
public class AuthenticationServiceTest {

	@Autowired
	private AuthenticationService authenticationService;

	@Test
	public void validateSuccessAuthenticationTest() {
		MetroCard cardHolder = new MetroCard();
		cardHolder.setCard_pin("$2a$10$HWOWQw3hTu.UkxPeZltlQu2YDVMmZF2dUrYvueLvwHLoOd8jgYfi2");
		Boolean isAccountAuthentic = this.authenticationService.authenticateCardHolderAccount(cardHolder, "1234");
		Assertions.assertTrue(isAccountAuthentic);
	}

	@Test
	public void validateFailedAuthenticationTest() {
		MetroCard cardHolder = new MetroCard();
		cardHolder.setCard_pin("$2a$10$HWOWQw3hTu.UkxPeZltlQu2YDVMmZF2dUrYvueLvwHLoOd8jgYfi2");
		Boolean isAccountAuthentic = this.authenticationService.authenticateCardHolderAccount(cardHolder, "4321");
		Assertions.assertFalse(isAccountAuthentic);
	}
}
