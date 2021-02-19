package org.patrickbelanger.investment.tool.factory;

import org.patrickbelanger.investment.tool.factory.implementation.CompanyOverviewDomParserMarketBeat;
import org.patrickbelanger.investment.tool.model.CompanyOverview;
import org.patrickbelanger.investment.tool.type.Exchange;

/**
 * 
 * @author Patrick Bélanger
 *
 */
public class CompanyOverviewFactory {

  public static CompanyOverview getCompanyOverview(String symbol) {
    return null;
  }
  
  public static CompanyOverview getCompanyOverview(String symbol, Exchange exchange) {
  	if ((exchange.equals(Exchange.TSX)) || (exchange.equals(Exchange.TSXV))) {
  		return new CompanyOverviewDomParserMarketBeat(symbol).getCompanyOverview();
  	} else {
  		
  	}
    return null;
  }
  
}
