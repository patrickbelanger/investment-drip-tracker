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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patrickbelanger.investment.tool.response.ResponseCurrencyExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * CurrencyExchange unit test
 * 
 * @author Patrick Belanger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyExchangeTest.class)
@SpringBootConfiguration
public class CurrencyExchangeTest {

  @Value("${api.key}")
  private String apiKey;

  @Value("${api.currency.exchange.endpoint}")
  private String apiEndpoint;
  
  private RestTemplate restTemplate;

  private Currency fromCurrency;
  private Currency toCurrency;
  
  @Before
  public void setUp() {
    restTemplate = new RestTemplate();
    this.fromCurrency = Currency.getInstance("USD");
    this.toCurrency = Currency.getInstance("CAD");
  }

  @Test
  public void currencyExchange_shouldBeAbleToGetCurrentExchangeRate() {
    ResponseEntity<String> response = restTemplate.getForEntity(
        String.format(apiEndpoint, fromCurrency.toString(), toCurrency.toString(), 
            apiKey), String.class);
    String[] exchangeRate = response.getBody().split("\""); // Exchange rate (position 21)
    assertTrue(exchangeRate.length == 39);  // Splitting the response will return 39 parts, including the exchange rate
    ResponseCurrencyExchange responseCurrencyExchange = 
        new ResponseCurrencyExchange(fromCurrency, toCurrency, Double.valueOf(exchangeRate[21]));
    assertEquals("USD", responseCurrencyExchange.getFromCurrency().toString());
    assertEquals("CAD", responseCurrencyExchange.getToCurrency().toString());
    assertNotNull(responseCurrencyExchange.getPrice());
  }

  @After
  public void tearDown() {
     if (restTemplate != null) {
      restTemplate = null;
    }
  }

}
