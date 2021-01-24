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

package org.patrickbelanger.investment.tool.type;

/**
 * 
 * @author Patrick Belanger
 *
 */
public enum Exchange {
  
  BATS("BATS", "Better Alternative Trading System"),
  NASDAQ("NASDAQ", "NASDAQ"),
  NYSE("NYSE", "New York Stock Exchange"),
  NYSE_ARCA("NYSE ARCA", "NYSE ARCA"),
  NYSE_MKT("NYSE MKT", "NYSE MKT"),
  TSX("TSX", "Toronto Stock Exchange"),
  TSXV("TSXV", "TSX Venture Exchange");
  
  private String exchangeCode;
  private String description;
  
  private Exchange(String exchangeCode, String description) {
    this.exchangeCode = exchangeCode;
    this.description = description;
  }
  
  @Override
  public String toString() {
    return this.exchangeCode;
  }
  
  public String getDescription() {
    return this.description;
  }
  
}
