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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.patrickbelanger.investment.tool.configuration.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application main entry point
 * 
 * @author Patrick Belanger
 *
 */
@SpringBootApplication
@ComponentScan("org.patrickbelanger.investment.tool")
public class Application {

	private static ConfigurableApplicationContext applicationContext;
	private static ApplicationConfig applicationConfig;
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static ApplicationConfig getApplicationConfig() {
    return applicationConfig;
  }
	
	public static ConfigurableApplicationContext getApplicationContext() {
	  return applicationContext;
	}
	
	public static void main(String[] args) {
		applicationContext = SpringApplication.run(Application.class);
		applicationConfig = applicationContext.getBean(ApplicationConfig.class);
		launchBrowser();
	}
	
	private static void launchBrowser() {
		if (applicationConfig.isBrowserLaunch()) {
			System.setProperty("java.awt.headless", "false");
			String applicationUrl = String.format(applicationConfig.getServerUrl(), applicationConfig.getServerPort());
      try {
			  logger.info("Open application in web browser (url: {})", applicationUrl);
        URI homepage = new URI(applicationUrl);
				Desktop.getDesktop().browse(homepage);
			} catch (URISyntaxException | IOException e) {
			  logger.info("Unable to load web browser on local host");
        logger.error(e.getMessage());
			  applicationContext.close();
			}
		}
	}

}
