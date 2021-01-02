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

import java.util.List;

import org.patrickbelanger.investment.tool.model.Holding;
import org.patrickbelanger.investment.tool.service.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * HoldingServiceImplementation class
 * 
 * @author Patrick Belanger
 *
 */
@Service
public class HoldingServiceImplementation implements HoldingService {

  @Autowired  
  private JdbcTemplate jdbcTemplate;    
  
  public int addHolding(final Holding Holding) {
    return jdbcTemplate.update("INSERT INTO HOLDINGS(PORTFOLIO_ID, SYMBOL, EXCHANGE, NUMBER_OF_SHARES, BOOK_VALUE, "
        + "DIVIDEND_YIELD, DIVIDEND_PER_SHARE, SHARE_CURRENT_VALUE, SHARE_MAX_VALUE) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", 
        Holding.getPortfolioId(), Holding.getSymbol(), Holding.getExchange(), Holding.getNumberOfShares(), 
        Holding.getBookValue(), Holding.getDividendYield(), Holding.getDividendPerShare(), 
        Holding.getShareCurrentValue(), Holding.getShareMaxValue());
  }
  
  @Override
  public ResponseEntity<List<Holding>> getHoldings() {
    return new ResponseEntity<List<Holding>>(
        jdbcTemplate.query("SELECT * FROM HOLDINGS", new BeanPropertyRowMapper<Holding>(Holding.class)),
        HttpStatus.OK);
  }

  public int updateHolding(final int id, final Holding Holding) {
    return jdbcTemplate.update("UPDATE HOLDING SET PORTFOLIO_ID=?, SYMBOL=?, EXCHANGE=?, NUMBER_OF_SHARES=?, "
        + "BOOK_VALUE=?, DIVIDEND_YIELD=?, DIVIDEND_PER_SHARE=?, SHARE_CURRENT_VALUE=?, SHARE_MAX_VALUE=?",
        Holding.getPortfolioId(), Holding.getSymbol(), Holding.getExchange(), Holding.getNumberOfShares(), 
        Holding.getBookValue(), Holding.getDividendYield(), Holding.getDividendPerShare(), 
        Holding.getShareCurrentValue(), Holding.getShareMaxValue());
  }
  
  public int deleteHolding(final int id) {
    return jdbcTemplate.update("DELETE FROM HOLDING WHERE ID = ?", id);
  }
  
}
