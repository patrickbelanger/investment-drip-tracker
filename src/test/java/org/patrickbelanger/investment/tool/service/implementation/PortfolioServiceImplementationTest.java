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
import static org.junit.jupiter.api.Assertions.assertNull;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * CompanyOverview unit test
 * 
 * @author Patrick Belanger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortfolioServiceImplementationTest.class)
@SpringBootConfiguration
@EnableAutoConfiguration
public class PortfolioServiceImplementationTest {

  @Value("${api.key}")
  private String apiKey;

  @Value("${api.portfolio.endpoint.delete}")
  private String apiEndpointDelete;

  @Value("${api.portfolio.endpoint.get}")
  private String apiEndpointGet;

  @Value("${api.portfolio.endpoint.post}")
  private String apiEndpointPost;

  @Value("${api.portfolio.endpoint.put}")
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

  private String setValidRequest() throws JSONException {
    return setValidRequest("Integration testing");
  }
  
  private String setValidRequest(String description) throws JSONException {
    JSONObject portfolioObject = new JSONObject();
    portfolioObject.put("accountType", "TFSA");
    portfolioObject.put("accountTypeOtherDescription", description);
    return portfolioObject.toString();
  }
  

  private String setInvalidRequestAccountTypeValue() throws JSONException {
    JSONObject portfolioObject = new JSONObject();
    portfolioObject.put("accountType", "TSFA");
    portfolioObject.put("accountTypeOtherDescription", "Integration testing (will trigger HttpClientErrorException)");
    return portfolioObject.toString();
  }

  private String setInvalidRequestInvalidJsonAttribute() throws JSONException {
    JSONObject portfolioObject = new JSONObject();
    portfolioObject.put("accountType", "TFSA");
    portfolioObject.put("invalidJsonAttribute", "Integration testing (will trigger HttpClientErrorException)");
    return portfolioObject.toString();
  }

  private ResponseEntity<Object> setRequest(String json) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> request = new HttpEntity<String>(json, headers);
    return restTemplate.postForEntity(String.format("%s%s", applicationUrl, apiEndpointPost), request, Object.class);
  }

  /* Post */
  
  @Test(expected = HttpClientErrorException.class)
  public void portfolioService_shouldTriggerHttpClientErrorExceptionCausedInvalidRequestAccountTypeValue()
      throws JSONException {
    ResponseEntity<Object> response = setRequest(setInvalidRequestAccountTypeValue());
    assertNull(response);
  }

  @Test(expected = HttpClientErrorException.class)
  public void portfolioService_shouldTriggerHttpClientErrorExceptionCausedInvalidJsonAttribute() throws JSONException {
    ResponseEntity<Object> response = setRequest(setInvalidRequestInvalidJsonAttribute());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
 
  @Test
  public void portfolioService_shouldBeAbleToSaveANewPortfolio() throws JSONException {
    ResponseEntity<Object> response = setRequest(setValidRequest());
    assertEquals(HttpStatus.OK, response.getStatusCode());
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
