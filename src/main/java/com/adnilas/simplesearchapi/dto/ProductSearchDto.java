package com.adnilas.simplesearchapi.dto;

import java.math.BigDecimal;

public class ProductSearchDto {

  private String type;

  private BigDecimal minPrice;

  private BigDecimal maxPrice;

  private String city;

  private String property;

  private String color;

  private Integer minimumLimit;

  private Integer maximumLimit;


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

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getColor() { return color; }

  public void setColor(String color) {
    this.color = color;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public Integer getMinimumLimit() {
    return minimumLimit;
  }

  public void setMinimumLimit(Integer minimumLimit) {
    this.minimumLimit = minimumLimit;
  }

  public Integer getMaximumLimit() {
    return maximumLimit;
  }

  public void setMaximumLimit(Integer maximumLimit) {
    this.maximumLimit = maximumLimit;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ProductSearchDto{");
    sb.append("type='").append(type).append('\'');
    sb.append(", minPrice=").append(minPrice);
    sb.append(", maxPrice=").append(maxPrice);
    sb.append(", city='").append(city).append('\'');
    sb.append(", property='").append(property).append('\'');
    sb.append(", color='").append(color).append('\'');
    sb.append(", minimumLimit=").append(minimumLimit);
    sb.append(", maximumLimit=").append(maximumLimit);
    sb.append('}');
    return sb.toString();
  }
}
