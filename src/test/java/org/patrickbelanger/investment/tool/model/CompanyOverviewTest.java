// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.patrickbelanger.investment.tool.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patrickbelanger.investment.tool.factory.CompanyOverviewFactory;
import org.patrickbelanger.investment.tool.type.Exchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * CompanyOverview unit test
 * 
 * @author Patrick Belanger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanyOverviewTest.class)
@SpringBootConfiguration
public class CompanyOverviewTest {

	@Value("${api.key}")
	private String apiKey;

	@Value("${api.company.overview.endpoint}")
	private String apiEndpoint;

	private RestTemplate restTemplate;

	private final String EXPECTED_STOCK = "IBM";

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	/*
	@Test
	public void companyOverview_shouldBeAbleToStoreCompanyOverviewFromService() {
		ResponseEntity<CompanyOverview> response = restTemplate
				.getForEntity(String.format(apiEndpoint, EXPECTED_STOCK, apiKey), CompanyOverview.class);
		CompanyOverview companyOverview = response.getBody();
		assertEquals(EXPECTED_STOCK, companyOverview.getSymbol());
	}
	*/

  @Test
  public void companyOverview_shouldBeAbleToStoreCompanyOverviewTsxExchangeFromService() {
    CompanyOverview companyOverview = CompanyOverviewFactory.getCompanyOverview("T", Exchange.TSX);
    assertEquals("T", companyOverview.getSymbol());
  }

	@After
	public void tearDown() {
		if (restTemplate != null) {
			restTemplate = null;
		}
	}

}
