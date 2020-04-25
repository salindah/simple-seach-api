package com.adnilas.simplesearchapi.model;

import com.adnilas.simplesearchapi.dto.ProductDto;

public class Product {

  private static String TYPE_PHONE = "phone";
  private static String TYPE_SUBSCRIPTION = "subscription";
  private static String COLOR = "color";
  private static String GB_LIMIT = "gb_limit";

  private int id;

  private String productType;

  private String color;

  private int gbLimit;

  private double price;

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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

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

  public ProductDto toDto(){
    ProductDto dto = new ProductDto();
    dto.setType(this.productType);
    dto.setPrice(this.price);
    if(TYPE_PHONE.equals(this.productType)){
      dto.setProperties(COLOR + ":" + this.color);
    } else if (TYPE_SUBSCRIPTION.equals(this.productType)){
      dto.setProperties( GB_LIMIT + ":" + this.gbLimit);
    }
    dto.setStoreAddress(this.street + ", " + this.city);
    return dto;
  }
}
