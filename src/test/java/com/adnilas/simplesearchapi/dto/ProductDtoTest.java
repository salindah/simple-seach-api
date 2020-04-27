package com.adnilas.simplesearchapi.dto;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class ProductDtoTest {

  @Test
  public void testProductDto(){

    ProductDto productDto = new ProductDto();
    productDto.setType("subscription");
    productDto.setProperties("gb_limit:10");
    productDto.setPrice( new BigDecimal("704.00"));
    productDto.setStoreAddress("Dana gärdet, Stockholm");

    Assert.assertEquals("subscription", productDto.getType());
    Assert.assertEquals("gb_limit:10", productDto.getProperties());
    Assert.assertEquals("704.00", productDto.getPrice().toString());
    Assert.assertEquals("Dana gärdet, Stockholm", productDto.getStoreAddress());
  }
}
