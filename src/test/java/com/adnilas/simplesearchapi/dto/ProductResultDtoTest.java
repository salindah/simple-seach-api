package com.adnilas.simplesearchapi.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ProductResultDtoTest {

  @Test
  public void testProductResultDto(){

    ProductResultDto productResultDto = new ProductResultDto();

    ProductDto productDto = new ProductDto();
    productDto.setType("subscription");
    productDto.setProperties("gb_limit:10");
    productDto.setPrice( new BigDecimal("704.00"));
    productDto.setStoreAddress("Dana gärdet, Stockholm");
    List<ProductDto> dtoList = Arrays.asList(productDto);

    productResultDto.setData(dtoList);

    Assert.assertNotNull(dtoList);
    Assert.assertEquals(1, dtoList.size());
  }
}
