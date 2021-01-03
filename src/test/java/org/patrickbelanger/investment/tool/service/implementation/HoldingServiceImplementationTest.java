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

package org.patrickbelanger.investment.tool.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patrickbelanger.investment.tool.Application;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * HoldingServiceImplementationTest unit test
 * 
 * @author Patrick Belanger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HoldingServiceImplementationTest.class)
@SpringBootConfiguration
@EnableAutoConfiguration
public class HoldingServiceImplementationTest {

  @Value("${api.key}")
  private String apiKey;

  @Value("${api.holding.endpoint.delete}")
  private String apiEndpointDelete;

  @Value("${api.holding.endpoint.get}")
  private String apiEndpointGet;

  @Value("${api.holding.endpoint.post}")
  private String apiEndpointPost;

  @Value("${api.holding.endpoint.put}")
  private String apiEndpointPut;

  @Value("${server.port}")
  private String serverPort;

  @Value("${server.url}")
  private String serverUrl;

  private String applicationUrl;
  private RestTemplate restTemplate;

  @Before
  public void setUp() {
    restTemplate = new RestTemplate();
    applicationUrl = String.format(serverUrl, serverPort);
    Application.main(null);
  }

  /* Data provider */
  
  private String setValidRequest() throws JSONException {
    JSONObject holdingObject = new JSONObject();
    holdingObject.put("portfolioId", 1);
    holdingObject.put("symbol", "T.TO");
    holdingObject.put("exchange", "TSX");
    holdingObject.put("numberOfShares", 10);
    holdingObject.put("bookValue", 12.43);
    holdingObject.put("dividendYield", 3.54);
    holdingObject.put("dividendPerShare", 0.10);
    holdingObject.put("shareCurrentValue", 13.50);
    holdingObject.put("shareMaxValue", 15.50);
    return holdingObject.toString();
  }
    
  /* Utility */
  
  private ResponseEntity<Object> setRequest(String json) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<String>(json, headers);
    return restTemplate.postForEntity(String.format("%s%s", applicationUrl, apiEndpointPost), request, Object.class);
  }
  
  private ResponseEntity<Object> updateRequest(String json, int id) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<String>(json, headers);
    return restTemplate.exchange(
        String.format("%s%s%s", applicationUrl, apiEndpointPut, id), HttpMethod.PUT, request, Object.class);
  }
  
  /* Delete */
  
  /* Get */
  
  /* Post */
  
  @Test
  public void holdingService_add_shouldBeAbleToSaveANewHolding() throws JSONException {
    ResponseEntity<Object> response = setRequest(setValidRequest());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  /* Update */
  @Test
  public void holdingService_update_shouledBeAbleToUpdateAnExistingHolding() throws JSONException {
    ResponseEntity<Object> response = setRequest(setValidRequest()); // Add a row
    response = updateRequest(setValidRequest(), 1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody());
  }
  
  @After
  public void tearDown() {
    if (restTemplate != null) {
      restTemplate = null;
    }
    if (Application.getApplicationContext() != null) {
      Application.getApplicationContext().close();
    }
  }

}
