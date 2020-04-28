package com.adnilas.simplesearchapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * This class is used to transfer product data from search service to connector end point.
 *
 * @author  Salinda Hettiarachchi
 */
public class ProductDto {

  private String type;

  private String properties;

  private BigDecimal price;

  @JsonProperty("store_address")
  private String storeAddress;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getProperties() {
    return properties;
  }

  public void setProperties(String properties) {
    this.properties = properties;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getStoreAddress() {
    return storeAddress;
  }

  public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ProductDto{");
    sb.append("type='").append(type).append('\'');
    sb.append(", properties='").append(properties).append('\'');
    sb.append(", price=").append(price);
    sb.append(", storeAddress='").append(storeAddress).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
