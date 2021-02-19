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

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.patrickbelanger.investment.tool.model.CompanyOverview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import lombok.Getter;
import lombok.Setter;

/**
 * Company Overview Implementation using JSoup DOM/HTML parser to gather required information (use as a fallback
 * and temporary approach until I found another (and more complete) API). Since it's an home brew project at the
 * moment, this approach helps gathering required information about a stock.
 * 
 * @author Patrick Belanger
 *
 */
public abstract class CompanyOverviewDomParser implements CompanyOverviewService {

  private Logger logger = LoggerFactory.getLogger(CompanyOverviewDomParser.class);
  
  @Getter
  @Setter
  private CompanyOverview companyOverview;
  
  @Getter
  @Setter
  private String providerUrl;

  @Getter
  @Setter
  protected Document document;
  
  @Getter
  @Setter
  protected Elements elements;
  
  @Getter
  @Setter
  private HtmlPage htmlPage; 
  
  @Getter
  @Setter
  private WebClient webClient;
  
  public CompanyOverviewDomParser(String providerUrl, String symbol) {
    try {
      setProviderUrl(providerUrl);
      setWebClient(new WebClient());
      getWebClient().getOptions().setJavaScriptEnabled(true);
      getWebClient().getOptions().setCssEnabled(false);
      getWebClient().getOptions().setUseInsecureSSL(true);
      getWebClient().getOptions().setThrowExceptionOnFailingStatusCode(false);
      getWebClient().getCookieManager().setCookiesEnabled(true);
      getWebClient().setAjaxController(new NicelyResynchronizingAjaxController());
      getWebClient().waitForBackgroundJavaScript(15000);
      getWebClient().getOptions().setThrowExceptionOnScriptError(false);
      setHtmlPage(getWebClient().getPage(String.format(getProviderUrl(), symbol)));
      setDocument(Jsoup.parse(getHtmlPage().asXml()));
    } catch (IOException e) {
      logger.debug(e.getLocalizedMessage());
    }
  }

}
