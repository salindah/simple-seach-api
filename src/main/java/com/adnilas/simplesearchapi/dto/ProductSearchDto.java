package com.adnilas.simplesearchapi.dto;

import java.math.BigDecimal;

public class ProductSearchDto {

  private String type;

  private BigDecimal minPrice;

  private BigDecimal maxPrice;


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public BigDecimal getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(BigDecimal minPrice) {
    this.minPrice = minPrice;
  }

  public BigDecimal getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(BigDecimal maxPrice) {
    this.maxPrice = maxPrice;
  }
}
