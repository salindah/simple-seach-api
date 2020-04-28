package com.adnilas.simplesearchapi.model;

import com.adnilas.simplesearchapi.dto.ProductDto;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

  @Test
  public void testProduct(){
    Product product = new Product();

    product.setId(12);
    product.setProductType("subscription");
    product.setProperty("gb_limit");
    product.setGbLimit(10);
    product.setStreet("Octavia gränden");
    product.setCity("Stockholm");
    product.setColor("vit");

    Assert.assertEquals(12, product.getId());
    Assert.assertEquals("subscription", product.getProductType());
    Assert.assertEquals("gb_limit", product.getProperty());
    Assert.assertEquals(10, product.getGbLimit());
    Assert.assertEquals("Octavia gränden", product.getStreet());
    Assert.assertEquals("Stockholm", product.getCity());
    Assert.assertEquals("vit", product.getColor());
  }

  @Test
  public void testToDto(){
    Product product = new Product();

    product.setProductType("subscription");
    product.setProperty("gb_limit");
    product.setGbLimit(10);
    product.setPrice(new BigDecimal("200"));
    product.setStreet("Octavia gränden");
    product.setCity("Stockholm");

    ProductDto productDto = product.toDto();
    Assert.assertEquals("subscription", productDto.getType());
    Assert.assertEquals("gb_limit:10", productDto.getProperties());
    Assert.assertEquals("200", productDto.getPrice().toString());
    Assert.assertEquals("Octavia gränden, Stockholm", productDto.getStoreAddress());
  }
}
