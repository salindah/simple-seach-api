package com.adnilas.simplesearchapi.dto;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class ProductSearchDtoTest {

  @Test
  public void testProductSearchDto(){
    ProductSearchDto productSearchDto = new ProductSearchDto();

    productSearchDto.setType("subscription");
    productSearchDto.setMinPrice(new BigDecimal("100"));
    productSearchDto.setMaxPrice(new BigDecimal("1000"));
    productSearchDto.setCity("Stockholm");
    productSearchDto.setProperty("gb_limit");
    Integer minimumLimit = new Integer(10);
    productSearchDto.setMinimumLimit(minimumLimit);
    Integer maximumLimit = new Integer(60);
    productSearchDto.setMaximumLimit(maximumLimit);

    Assert.assertEquals("subscription", productSearchDto.getType());
    Assert.assertEquals("100", productSearchDto.getMinPrice().toString());
    Assert.assertEquals("1000", productSearchDto.getMaxPrice().toString());
    Assert.assertEquals("Stockholm", productSearchDto.getCity());
    Assert.assertEquals("gb_limit", productSearchDto.getProperty());
    Assert.assertEquals( minimumLimit, productSearchDto.getMinimumLimit());
    Assert.assertEquals( maximumLimit, productSearchDto.getMaximumLimit());
  }
}
