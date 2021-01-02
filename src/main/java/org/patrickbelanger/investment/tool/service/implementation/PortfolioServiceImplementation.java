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

import org.patrickbelanger.investment.tool.model.Portfolio;
import org.patrickbelanger.investment.tool.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * PortfolioServiceImplementation class
 * 
 * @author Patrick Belanger
 *
 */
@Service
public class PortfolioServiceImplementation implements PortfolioService {

  @Autowired  
  private JdbcTemplate jdbcTemplate;    
  
  public int addPortfolio(final Portfolio portfolio) {
    return jdbcTemplate.update("INSERT INTO PORTFOLIOS(ACCOUNT_TYPE, ACCOUNT_TYPE_OTHER_DESCRIPTION) VALUES (?, ?)", 
        portfolio.getAccountType().toString(), portfolio.getAccountTypeOtherDescription());
  }
  
  @Override
  public ResponseEntity<List<Portfolio>> getPortfolios() {
    return new ResponseEntity<List<Portfolio>>(
        jdbcTemplate.query("SELECT * FROM PORTFOLIOS", new BeanPropertyRowMapper<Portfolio>(Portfolio.class)),
        HttpStatus.OK);
  }

  public int updatePortfolio(final int id, final Portfolio portfolio) {
    return jdbcTemplate.update("UPDATE PORTFOLIOS SET ACCOUNT_TYPE=?, ACCOUNT_TYPE_OTHER_DESCRIPTION=? WHERE ID=?",
        portfolio.getAccountType().toString(), portfolio.getAccountTypeOtherDescription(), id);
  }
  
  public int deletePortfolio(final int id) {
    return jdbcTemplate.update("DELETE FROM PORTFOLIOS WHERE ID = ?", id);
  }
  
}
