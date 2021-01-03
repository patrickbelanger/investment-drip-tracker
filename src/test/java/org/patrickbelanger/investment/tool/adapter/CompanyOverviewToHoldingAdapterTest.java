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

package org.patrickbelanger.investment.tool.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patrickbelanger.investment.tool.model.CompanyOverview;
import org.patrickbelanger.investment.tool.model.Holding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CompanyOverviewToHoldingAdapterTest.class)
@SpringBootConfiguration
public class CompanyOverviewToHoldingAdapterTest {

  @Value("${api.key}")
  private String apiKey;

  @Value("${api.company.overview.endpoint}")
  private String apiEndpoint;

  private Holding holding;
  private RestTemplate restTemplate;
  private final String EXPECTED_STOCK = "IBM";
  
  
  @Before
  public void setUp() {
    restTemplate = new RestTemplate();
    holding = new Holding();
  }

  @Test(expected = InvocationTargetException.class)
  public void companyOverviewToHoldingAdapter_shouldRaiseAnExceptionIfWetryToInstantiateThatUtilityClass() 
      throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, 
      IllegalArgumentException, InvocationTargetException {
    Constructor<CompanyOverviewToHoldingAdapter> c = CompanyOverviewToHoldingAdapter.class.getDeclaredConstructor();
    c.setAccessible(true);
    c.newInstance();
  }
  
  @Test
  public void companyOverviewToHoldingAdapter_shouldBeAbleToConvertACompanyOverviewObjectToAHoldingObject() {
    ResponseEntity<CompanyOverview> response = restTemplate
        .getForEntity(String.format(apiEndpoint, EXPECTED_STOCK, apiKey), CompanyOverview.class);
    CompanyOverview companyOverview = response.getBody();
    holding = CompanyOverviewToHoldingAdapter.convert(companyOverview, holding);
    assertEquals(companyOverview.getBookValue(), holding.getBookValue());
    assertEquals(companyOverview.getDividendPerShare(), holding.getDividendPerShare());
    assertEquals(companyOverview.getDividendYield(), holding.getDividendYield());
    assertEquals(0, holding.getNumberOfShares()); // This is provided manually by the user
    assertEquals(0.0, holding.getShareCurrentValue()); 
      // Another service call (quote service) is required to get that value
    assertEquals(companyOverview.getSymbol(), holding.getSymbol());
    assertEquals(EXPECTED_STOCK, holding.getSymbol());
  }

  @After
  public void tearDown() {
    if (restTemplate != null) {
      restTemplate = null;
    }
    if (holding != null) {
      holding = null;
    }
  }
  
}
