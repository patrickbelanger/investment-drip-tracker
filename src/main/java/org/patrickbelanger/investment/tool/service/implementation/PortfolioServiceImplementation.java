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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Patrick Belanger
 *
 */
@Service
public class PortfolioServiceImplementation implements PortfolioService {

  @Autowired  
  private JdbcTemplate jdbcTemplate;    
  
  public int addPortfolio(Portfolio portfolio) {
    return jdbcTemplate.update("INSERT INTO PORTFOLIOS (ACCOUNT_TYPE, ACCOUNT_TYPE_OTHER_DESCRIPTION) VALUE (?, ?)", 
        new Object[] { portfolio.getAccountType(), portfolio.getAccountTypeOtherDescription()});
  }
  
  @Override
  public List<Portfolio> getPortfolios() {
    return jdbcTemplate.query("SELECT * FROM PORTFOLIOS", new BeanPropertyRowMapper<Portfolio>(Portfolio.class));
  }

}
