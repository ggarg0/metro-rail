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

package com.demo.metrorail.service.business.stationdetails;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.metrorail.data.service.StationDetailsDataService;
import com.demo.metrorail.dto.StationFootfallResponse;
import com.demo.metrorail.entity.Stations;

@SpringBootTest
public class StationDetailsServiceTest {

	@Mock
	private StationDetailsDataService stationDetailsService;

	@InjectMocks
	private StationDetailsServiceImpl stationDetailsServiceImpl;

	@BeforeEach
	public void setUp() {

		when(stationDetailsService.getAllStationsFootfall()).then(new Answer<List<StationFootfallResponse>>() {
			@Override
			public List<StationFootfallResponse> answer(InvocationOnMock invocationOnMock) throws Throwable {
				List<StationFootfallResponse> response = new ArrayList<>();
				response.add(new StationFootfallResponse("A1", 2, 3, ""));
				response.add(new StationFootfallResponse("A2", 1, 1, ""));
				response.add(new StationFootfallResponse("A3", 4, 2, ""));
				response.add(new StationFootfallResponse("A4", 3, 6, ""));
				response.add(new StationFootfallResponse("A5", 5, 4, ""));
				response.add(new StationFootfallResponse("A6", 3, 1, ""));
				return response;
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
	}

	@Test
	public void testValidStationEnquiry() {
		List<Stations> stations = this.stationDetailsServiceImpl.getStationDetails("A1", "A8");
		Assertions.assertEquals(2, stations.size());
	}

	@Test
	public void testgetAllStationsFootfall() {
		List<StationFootfallResponse> response = this.stationDetailsService.getAllStationsFootfall();
		Assertions.assertEquals(6, response.size());
	}

}
