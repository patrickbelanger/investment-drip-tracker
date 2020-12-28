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

package org.patrickbelanger.investment.tool;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Patrick Belanger
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
@SpringBootConfiguration
public class ApplicationTest {
  
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();
  
  @Test
  public void applicationTest_shouldTheApplicationStarts() {
    assertNull(Application.getApplicationConfig());
    Application.main(new String[] {});
    assertNotNull(Application.getApplicationContext());
    assertNotNull(Application.getApplicationConfig());
    assertTrue(Application.getApplicationConfig().isBrowserLaunch());
  }
  
}
