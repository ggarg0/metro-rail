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
import com.demo.metrorail.data.service.CardDetailsDataService;
import com.demo.metrorail.data.service.StationDetailsDataService;
import com.demo.metrorail.dto.JourneyDetailsRequest;
import com.demo.metrorail.dto.JourneyDetailsResponse;
import com.demo.metrorail.entity.MetroCard;
import com.demo.metrorail.entity.Stations;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.security.AuthenticationService;

@SpringBootTest
public class JourneyDetailsServiceTest {

	@Mock
	private CardDetailsDataService cardDetailsDataService;

	@Mock
	private StationDetailsDataService stationDetailsDataService;

	@InjectMocks
	private JourneyDetailsServiceImpl journeyDetailsServiceImpl;

	@Autowired
	private AuthenticationService authenticationService;

	@BeforeEach
	public void setUp() {

		when(cardDetailsDataService.getAccountDetailsForCardNumber("11111")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				return new MetroCard(1L, "Adam", "Sandler", "adam_s", "adam@hollywood.com", "11111",
						"$2a$10$AVI6e8rkyn.9Y93Yrbu9/.YM8plIBGZ33PUQy57U1fXw34uXT9mNC", 100L);
			}
		});

		when(stationDetailsDataService.getStationDetails("A1", "A8")).then(new Answer<List<Stations>>() {
			@Override
			public List<Stations> answer(InvocationOnMock invocationOnMock) throws Throwable {
				List<Stations> stations = new ArrayList<>();
				stations.add(new Stations(1L, "A1", 1, 0, 0, 0L));
				stations.add(new Stations(2L, "A8", 8, 0, 0, 87L));
				return stations;
			}
		});

		when(cardDetailsDataService.getAccountDetailsForCardNumber("unknown")).then(new Answer<MetroCard>() {
			@Override
			public MetroCard answer(InvocationOnMock invocationOnMock) throws Throwable {
				throw new CardNumberNotFoundException("Account not exists for card holder unknown");
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
	public void testInvalidStationEnquiry() {
		JourneyDetailsRequest req = new JourneyDetailsRequest("11111", "1234", "B1", "A8", "");
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(req);
		Assertions.assertTrue(MessageConstants.StationDetailsNotFound.equals(resp.getMessage()));
	}
	
	@Test
	public void testNullJourneyEnquiry() {
		JourneyDetailsResponse resp = this.journeyDetailsServiceImpl.getJourneyDetailsForUser(null);
		Assertions.assertTrue(MessageConstants.InvalidJourneyEnquiry.equalsIgnoreCase(resp.getMessage()));
	}

}
