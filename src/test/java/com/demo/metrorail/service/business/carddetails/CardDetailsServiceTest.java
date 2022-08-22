/*
 * *
 *  * The MIT License (MIT)
 *  * <p>
 *  * Copyright (c) 2022
 *  * <p>
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  * <p>
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  * <p>
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package com.demo.metrorail.service.business.carddetails;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.metrorail.constant.MessageConstants;
import com.demo.metrorail.data.service.CardDetailsDataService;
import com.demo.metrorail.dto.BalanceEnquiry;
import com.demo.metrorail.dto.BalanceEnquiryResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.security.AuthenticationService;

@SpringBootTest
public class CardDetailsServiceTest {

	@Mock
	private CardDetailsDataService cardDetailsDataService;

	@InjectMocks
	private CardDetailsServiceImpl cardDetailsServiceImpl;

	@Autowired
	private AuthenticationService authenticationService;

	@BeforeEach
	public void setUp() {
		when(cardDetailsDataService.getAllCardDetails()).then(new Answer<List<MetroCard>>() {
			@Override
			public List<MetroCard> answer(InvocationOnMock invocationOnMock) throws Throwable {
				List<MetroCard> cardHolders = new ArrayList<>();
				cardHolders.add(new MetroCard(1L, "Adam", "Sandler", "adam_s", "adam@hollywood.com", "11111",
						"$2a$10$AVI6e8rkyn.9Y93Yrbu9/.YM8plIBGZ33PUQy57U1fXw34uXT9mNC", 800L));
				cardHolders.add(new MetroCard(2L, "Brad", "Pitt", "brad_p", "brad@hollywood.com", "22222",
						"$2a$10$BkDnsFV/DA1.fIZ0l6BkEOcWxYMc3313nN2uD0YYNLKkwx6cgY/y.", 500L));
				cardHolders.add(new MetroCard(3L, "Mark", "Waugh", "mark_w", "mark@cricket.com", "33333",
						"$2a$10$2qcBY05Bo2W9VrlX7HUm1ONdc0OwTu5qF7382DOFlB3tnMydEpdl2", 200L));
				cardHolders.add(new MetroCard(4L, "Chris", "Gayle", "chris_g", "chris@cricket.com", "44444",
						"$2a$10$k2QUCfZf.t7dAHC3OvwdyO01yl5sNHhewaGBwyZRuCx/3LuzTQpPq", 700L));
				return cardHolders;
			}
		});

		when(cardDetailsDataService.getAccountDetailsForCardNumber("11111")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				return new MetroCard(1L, "Adam", "Sandler", "adam_s", "adam@hollywood.com", "11111",
						"$2a$10$AVI6e8rkyn.9Y93Yrbu9/.YM8plIBGZ33PUQy57U1fXw34uXT9mNC", 800L);
			}
		});

		when(cardDetailsDataService.getAccountDetailsForCardNumber("22222")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				return new MetroCard(2L, "Brad", "Pitt", "brad_p", "brad@hollywood.com", "22222",
						"$2a$10$BkDnsFV/DA1.fIZ0l6BkEOcWxYMc3313nN2uD0YYNLKkwx6cgY/y.", 500L);
			}
		});

		when(cardDetailsDataService.getAccountDetailsForCardNumber("33333")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				return new MetroCard(3L, "Mark", "Waugh", "mark_w", "mark@cricket.com", "33333",
						"$2a$10$2qcBY05Bo2W9VrlX7HUm1ONdc0OwTu5qF7382DOFlB3tnMydEpdl2", 200L);
			}
		});

		when(cardDetailsDataService.getAccountDetailsForCardNumber("44444")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				return new MetroCard(4L, "Chris", "Gayle", "chris_g", "chris@cricket.com", "44444",
						"$2a$10$k2QUCfZf.t7dAHC3OvwdyO01yl5sNHhewaGBwyZRuCx/3LuzTQpPq", 700L);
			}
		});

		when(cardDetailsDataService.getAccountDetailsForCardNumber("unknown")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				throw new CardNumberNotFoundException("Account not exists for card holder unknown");
			}
		});

		this.cardDetailsServiceImpl.setAuthenticationService(this.authenticationService);
	}

	@Test
	public void testMockData() {
		List<MetroCard> listOfAccounts = this.cardDetailsServiceImpl.getAllCardDetails();
		Assertions.assertEquals(4, listOfAccounts.size());
	}

	@Test
	public void testSuccessBalanceEnquiry() {
		BalanceEnquiry be = new BalanceEnquiry("11111", "1234");
		BalanceEnquiryResponse ber = this.cardDetailsServiceImpl.getCardBalanceForUser(be);
		Assertions.assertEquals(800, ber.getBalance());
	}

	@Test
	public void testSuccessBalanceEnquiryUser() {
		BalanceEnquiry be = new BalanceEnquiry("22222", "4321");
		BalanceEnquiryResponse ber = this.cardDetailsServiceImpl.getCardBalanceForUser(be);
		Assertions.assertEquals(500, ber.getBalance());
	}

	@Test
	public void testUnSuccessBalanceEnquiryUser() {
		BalanceEnquiry be = new BalanceEnquiry("22222", "4321");
		BalanceEnquiryResponse ber = this.cardDetailsServiceImpl.getCardBalanceForUser(be);
		Assertions.assertNotEquals(800, ber.getBalance());
	}
	
	@Test
	public void testIncorrectPinBalanceEnquiry() {
		BalanceEnquiry be = new BalanceEnquiry("11111", "1232");
		BalanceEnquiryResponse ber = this.cardDetailsServiceImpl.getCardBalanceForUser(be);
		Assertions.assertTrue(MessageConstants.InvalidPin.equals(ber.getMessage()));
	}

	@Test
	public void testInvalidCardHolderEnquiry() {
		BalanceEnquiry be = new BalanceEnquiry("unknown", "1234");
		BalanceEnquiryResponse ber = this.cardDetailsServiceImpl.getCardBalanceForUser(be);
		Assertions.assertTrue(MessageConstants.CardNumberNotFound.equals(ber.getMessage()));
	}

	@Test
	public void testNullBalanceEnquiry() {
		BalanceEnquiryResponse ber = this.cardDetailsServiceImpl.getCardBalanceForUser(null);
		System.out.println(ber);
		Assertions.assertTrue(MessageConstants.InvalidBalanceEnquiry.equalsIgnoreCase(ber.getMessage()));
	}

}
