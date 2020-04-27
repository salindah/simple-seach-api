package com.adnilas.simplesearchapi.model;

import com.adnilas.simplesearchapi.dto.ProductDto;
import java.math.BigDecimal;

public class Product {

  private static String COLOR = "color";
  private static String GB_LIMIT = "gb_limit";

  private int id;

  private String productType;

  private String property;

  private String color;

  private int gbLimit;

  private BigDecimal price;

  private String street;

  private String city;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getGbLimit() {
    return gbLimit;
  }

  public void setGbLimit(int gbLimit) {
    this.gbLimit = gbLimit;
  }

  public BigDecimal getPrice() { return price; }

  public void setPrice(BigDecimal price) { this.price = price; }

  public String getProperty() { return property; }

  public void setProperty(String property) { this.property = property; }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public ProductDto toDto() {
    ProductDto dto = new ProductDto();
    dto.setType(this.productType);
    dto.setPrice(this.price);
    if (COLOR.equals(this.property)) {
      dto.setProperties(this.property + ":" + this.color);
    } else if (GB_LIMIT.equals(this.property)) {
      dto.setProperties(this.property + ":" + this.gbLimit);
    }
    dto.setStoreAddress(this.street + ", " + this.city);
    return dto;
  }
}
