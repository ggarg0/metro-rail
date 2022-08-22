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

package com.demo.metrorail.service.business.journeydetails;

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
import com.demo.metrorail.dto.JourneyDetailsRequest;
import com.demo.metrorail.dto.JourneyDetailsResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.entity.Stations;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.exceptions.StationDetailsNotFoundException;
import com.demo.metrorail.security.AuthenticationService;
import com.demo.metrorail.service.business.carddetails.CardDetailsService;
import com.demo.metrorail.service.business.stationdetails.StationDetailsService;

@SpringBootTest
public class JourneyDetailsServiceTest {

	@Mock
	private CardDetailsService cardDetailsService;

	@Mock
	private StationDetailsService stationDetailsService;

	@InjectMocks
	private JourneyDetailsServiceImpl journeyDetailsServiceImpl;

	@Autowired
	private AuthenticationService authenticationService;

	@BeforeEach
	public void setUp() {

		when(cardDetailsService.getAccountDetailsForCardNumber("11111")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				return new MetroCard(1L, "Adam", "Sandler", "adam_s", "adam@hollywood.com", "11111",
						"$2a$10$AVI6e8rkyn.9Y93Yrbu9/.YM8plIBGZ33PUQy57U1fXw34uXT9mNC", 100L);
			}
		});

		when(stationDetailsService.getStationDetails("A1", "A8")).then(new Answer<List<Stations>>() {
			@Override
			public List<Stations> answer(InvocationOnMock invocationOnMock) throws Throwable {
				List<Stations> stations = new ArrayList<>();
				stations.add(new Stations(1L, "A1", 1, 0, 0, 0L));
				stations.add(new Stations(2L, "A8", 8, 0, 0, 87L));

				return stations;
			}
		});

		when(stationDetailsService.getStationDetails("A1", "A9")).then(new Answer<List<Stations>>() {
			@Override
			public List<Stations> answer(InvocationOnMock invocationOnMock) throws Throwable {
				List<Stations> stations = new ArrayList<>();
				stations.add(new Stations(1L, "A1", 1, 0, 0, 0L));
				stations.add(new Stations(2L, "A9", 9, 0, 0, 107L));
				return stations;
			}

		});

		when(stationDetailsService.getStationDetails("B1", "A8")).then(new Answer<List<Stations>>() {
			@Override
			public List<Stations> answer(InvocationOnMock invocationOnMock) throws Throwable {
				throw new StationDetailsNotFoundException(MessageConstants.StationDetailsNotFound);
			}
		});

		when(cardDetailsService.getAccountDetailsForCardNumber("unknown")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				throw new CardNumberNotFoundException(MessageConstants.CardNumberNotFound);
			}
		});

		this.journeyDetailsServiceImpl.setAuthenticationService(this.authenticationService);
	}

	@Test
	public void testJourneyDetailsForUserSuccess() {
		JourneyDetailsRequest req = new JourneyDetailsRequest("11111", "1234", "A1", "A8", "");
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(req);
		Assertions.assertTrue(resp.getMessage().contains(MessageConstants.Success));
	}

	@Test
	public void testNullJourneyEnquiry() {
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(null);
		Assertions.assertTrue(MessageConstants.InvalidJourneyEnquiry.equalsIgnoreCase(resp.getMessage()));
	}

	@Test
	public void testInvalidCardHolderEnquiry() {
		JourneyDetailsRequest req = new JourneyDetailsRequest("unknown", "1234", "A1", "A8", "");
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(req);
		Assertions.assertTrue(MessageConstants.CardNumberNotFound.equals(resp.getMessage()));
	}

	@Test
	public void testIncorrectPinBalanceEnquiry() {
		JourneyDetailsRequest req = new JourneyDetailsRequest("11111", "1232", "A1", "A8", "");
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(req);
		Assertions.assertTrue(MessageConstants.InvalidPin.equals(resp.getMessage()));
	}

	@Test
	public void testInsufficientAmountInAccountMessage() {
		JourneyDetailsRequest req = new JourneyDetailsRequest("11111", "1234", "A1", "A9", "");
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(req);
		Assertions.assertTrue(resp.getMessage().equals(MessageConstants.InsufficientAmountInAccountMessage));
	}

	@Test
	public void testInvalidStationEnquiry() {
		JourneyDetailsRequest req = new JourneyDetailsRequest("11111", "1234", "B1", "A8", "");
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(req);
		Assertions.assertTrue(MessageConstants.StationDetailsNotFound.equals(resp.getMessage()));
	}
	
}
