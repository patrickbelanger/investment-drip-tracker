package org.patrickbelanger.investment.tool.service.implementation;

import java.util.List;

import org.patrickbelanger.investment.tool.model.Portfolio;
import org.patrickbelanger.investment.tool.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImplementation implements PortfolioService {

  @Autowired  
  private JdbcTemplate jdbcTemplate;    
  
  @Override
  public List<Portfolio> getPortfolios() {
    return jdbcTemplate.queryForList("select * from portfolios", Portfolio.class);
  }

}
