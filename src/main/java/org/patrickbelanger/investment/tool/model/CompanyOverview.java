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

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

import lombok.Getter;
import lombok.Setter;

/**
 * CompanyOverview object/bean
 * 
 * This object store the company information, financial ratios, and other key
 * metrics for the equity specified. Data is generally refreshed on the same day
 * a company reports its latest earnings and financials.
 * 
 * @author Patrick Belanger
 */
@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class CompanyOverview {

	@Getter
	@Setter
	private String symbol;

	@Getter
	@Setter
	private String assetType;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private String exchange;

	@Getter
	@Setter
	private String currency;

	@Getter
	@Setter
	private String country;

	@Getter
	@Setter
	private String sector;

	@Getter
	@Setter
	private String industry;

	@Getter
	@Setter
	private String address;

	@Getter
	@Setter
	private String fullTimeEmployees;

	@Getter
	@Setter
	private String fiscalYearEnd;

	@JsonDeserialize(using = DateDeserializer.class)
	@JsonFormat(timezone="America/New_York")
	@Getter
	@Setter
	private Date latestQuarter;

	@Getter
	@Setter
	private long marketCapitalization;

	@JsonProperty("EBITDA")
	@Getter
	@Setter
	private String ebitda;

	@JsonProperty("PERatio")
	@Getter
	@Setter
	private double peratio;
	
	@JsonProperty("PEGRatio")
	@Getter
	@Setter
	private double pegratio;

	@Getter
	@Setter
	private double bookValue;

	@Getter
	@Setter
	private double dividendPerShare;

	@Getter
	@Setter
	private double dividendYield;

	@JsonProperty("EPS")
	@Getter
	@Setter
	private double eps;

	@JsonProperty("RevenuePerShareTTM")
	@Getter
	@Setter
	private double revenuePerShareTtm;
	
	@Getter
	@Setter
	private double profitMargin;
	
	@JsonProperty("OperatingMarginTTM")
	@Getter
	@Setter
	private double OperatingMarginTTM;
	
	@JsonProperty("ReturnOnAssetsTTM")
	@Getter
	@Setter
	private double ReturnOnAssetsTTM;
	
	@JsonProperty("ReturnOnEquityTTM")
	@Getter
	@Setter
	private double ReturnOnEquityTTM;
	
	@JsonProperty("RevenuePerShareTTM")
	@Getter
	@Setter
	private long RevenuePerShareTTM;

	@JsonProperty("GrossProfitTTM")
	@Getter
	@Setter
	private long GrossProfitTTM;
	
	@JsonProperty("DilutedEPSTTM")
	@Getter
	@Setter
	private double DilutedEPSTTM;
	
	@JsonProperty("QuarterlyEarningsGrowthYOY")
	@Getter
	@Setter
	private double quarterlyEarningsGrowthYOY;
	
	@JsonProperty("QuarterlyRevenueGrowthYOY")
	@Getter
	@Setter
	private double quarterlyRevenueGrowthYOY;
	
	@Getter
	@Setter
	private double analystTargetPrice;
	
	@JsonProperty("TrailingPE")
	@Getter
	@Setter
	private double trailingPE;
	
	@JsonProperty("ForwardPE")
	@Getter
	@Setter
	private double forwardPE;
	
	@JsonProperty("PriceToSalesRatioTTM")
	@Getter
	@Setter
	private double priceToSalesRatioTTM;
	
	@Getter
	@Setter
	private double priceToBookRatio;
	
	@JsonProperty("EVToRevenue")
	@Getter
	@Setter
	private double evToRevenue;
	
	@JsonProperty("EVToEBITDA")
	@Getter
	@Setter
	private double evToEbitda;

	@Getter
	@Setter
	private double beta;
	
	@JsonProperty("52WeekHigh")
	@Getter
	@Setter
	private double FiftyTwoWeekHigh;

	@JsonProperty("52WeekLow")
	@Getter
	@Setter
	private double FiftyTwoWeekLow;
	
	@JsonProperty("50DayMovingAverage")
	@Getter
	@Setter
	private double FiftyDayMovingAverage;
	
	@JsonProperty("200DayMovingAverage")
	@Getter
	@Setter
	private double TwoHundredDayMovingAverage;
	
	@Getter
	@Setter
	private double sharesOutstanding;
	
	@Getter
	@Setter
	private double sharesFloat;
	
	@Getter
	@Setter
	private double sharesShort;
	
	@Getter
	@Setter
	private double sharesShortPriorMonth;
	
	@Getter
	@Setter
	private double shortRatio;
	
	@Getter
	@Setter
	private double shortPercentOutstanding;
	
	@Getter
	@Setter
	private double shortPercentFloat;
	
	@Getter
	@Setter
	private double percentInsiders;
	
	@Getter
	@Setter
	private double percentInstitutions;
	
	@Getter
	@Setter
	private double forwardAnnualDividendRate;
	
	@Getter
	@Setter
	private double forwardAnnualDividendYield;
	
	@Getter
	@Setter
	private double payoutRatio;
	
	@JsonDeserialize(using = DateDeserializer.class)
	@Getter
	@Setter
	private Date DividendDate;
	
	@JsonDeserialize(using = DateDeserializer.class)
	@Getter
	@Setter
	private Date exDividendDate;
	
	@Getter
	@Setter
	private String lastSplitFactor;
	
	@JsonDeserialize(using = DateDeserializer.class)
	@Getter
	@Setter
	private Date lastSplitDate;

}
