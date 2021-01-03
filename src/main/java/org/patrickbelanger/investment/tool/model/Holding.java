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

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Patrick Belanger
 *
 */
@Component
public class Holding {

  @Id
  @Getter
  @Setter
  private int id;
  
  @Getter
  @Setter
  private int portfolioId;
  
  @Getter
  @Setter
  private String symbol;
  
  /**
   * On which exchange the symbol is traded
   */
  @Getter
  @Setter String exchange; 
  
  /**
   * Number of share you own/purchased
   */
  @Getter
  @Setter
  private int numberOfShares;
  
  @Getter
  @Setter
  private double bookValue;
  
  @Getter
  @Setter
  private double dividendYield;
  
  @Getter
  @Setter
  private double dividendPerShare;
  
  /**
   * Return the payment (dividend per share * number of shares)
   * 
   * Remark: if applicable, make sure to multiply the payment by the current exchange rate if applicable
   * Use case: you are a Canadian and you USD holding, you need to multiply by the current exchange rate to obtain
   * the payment in CAD.
   * 
   * E.g. 
   * - I own 4 shares of GES (USD/NYSE) 
   * - Dividend per share at $0.11 
   * - Current exchange rate USD to CAD = 1.31 
   * - (0.11 * 4) * 1.31 = $0.59 CAD
   * 
   * @return Dividend payment
   */
  @JsonIgnore
  public double getPayment() {
    return (getDividendPerShare() * getNumberOfShares()); // Multiply by the current exchange rate if applicable
  }
  
  @Getter
  @Setter
  private double shareCurrentValue;
  
  @Getter
  @Setter
  private double shareMaxValue;
  
  
}