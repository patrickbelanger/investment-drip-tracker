package org.patrickbelanger.investment.tool.type;

public enum Currency {
  
  CAD("CAD"),
  USD("USD");
  
  private String currencyValue;
  
  private Currency(String currencyValue) {
    this.currencyValue = currencyValue;
  }
  
  @Override
  public String toString() {
    return this.currencyValue;
  }
  
}
