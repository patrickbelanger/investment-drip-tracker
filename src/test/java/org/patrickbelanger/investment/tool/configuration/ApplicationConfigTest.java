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

package org.patrickbelanger.investment.tool.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ApplicationConfig unit test
 * 
 * @author Patrick Belanger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@ComponentScan("org.patrickbelanger.investment.tool")
public class ApplicationConfigTest {

  @Autowired
  private ApplicationContext context;
  private ApplicationConfig sut;
  
	@Before
	public void setUp() {
	  sut = context.getBean(ApplicationConfig.class);
	}

	/**
	 * Make sure expected values matches with application.properties file
	 */
	@Test
	public void applicationContext_shouldBeAbleToRetrieveValueFromPropertiesFile() {
	  assertTrue(sut.isBrowserLaunch());
	  assertEquals("8080", sut.getServerPort());
	  assertEquals("http://localhost:%s/", sut.getServerUrl());
	}

	@After
	public void tearDown() {
		if (sut != null) {
		  sut = null;
		}
	}

}
