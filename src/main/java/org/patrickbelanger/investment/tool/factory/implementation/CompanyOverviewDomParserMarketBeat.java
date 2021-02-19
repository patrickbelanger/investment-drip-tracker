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

package org.patrickbelanger.investment.tool.factory.implementation;

import org.patrickbelanger.investment.tool.model.CompanyOverview;

/**
 * 
 * @author Patrick Belanger
 *
 */
public class CompanyOverviewDomParserMarketBeat extends CompanyOverviewDomParser {

  private static final String PROVIDER_URL = "https://www.marketbeat.com/stocks/TSE/%s/dividend/";
  private static final String SELECTOR_INFORMATION_CONTAINER = "div[data-testid*=value]";
  
  public CompanyOverviewDomParserMarketBeat(String symbol) {
    super(PROVIDER_URL, symbol);
    setElements(getDocument().select(SELECTOR_INFORMATION_CONTAINER));
    setCompanyOverview(getCompanyOverviewFromProvider(symbol));
  }
  
  private CompanyOverview getCompanyOverviewFromProvider(String symbol) {
  	CompanyOverview companyOverview = new CompanyOverview(symbol);
  	return companyOverview;
  };
  
}
