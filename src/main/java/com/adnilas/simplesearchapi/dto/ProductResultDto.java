package com.adnilas.simplesearchapi.dto;

import java.util.List;

public class ProductResultDto {

  List<ProductDto> data;

  public List<ProductDto> getData() {
    return data;
  }

  public void setData(List<ProductDto> data) {
    this.data = data;
  }

}
