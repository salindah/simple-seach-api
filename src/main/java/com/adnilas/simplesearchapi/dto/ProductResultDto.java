package com.adnilas.simplesearchapi.dto;

import java.util.List;

/**
 * This class wraps product data list in to the 'data' variable.
 *
 * @author  Salinda Hettiarachchi
 */
public class ProductResultDto {

  List<ProductDto> data;

  public List<ProductDto> getData() {
    return data;
  }

  public void setData(List<ProductDto> data) {
    this.data = data;
  }

}
