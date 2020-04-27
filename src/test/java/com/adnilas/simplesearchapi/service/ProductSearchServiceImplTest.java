package com.adnilas.simplesearchapi.service;

import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.model.Product;
import com.adnilas.simplesearchapi.service.impl.ProductSearchServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductSearchServiceImplTest extends Mockito {

  @Mock
  JdbcTemplate template;

  @InjectMocks
  ProductSearchService productSearchService;

  @Before
  public void setup() {
    productSearchService = new ProductSearchServiceImpl();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetProducts(){
    ProductSearchDto filter = new ProductSearchDto();

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);

    when( template.query(any(String.class), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);
    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("phone", productResultDto.getData().get(0).getType());
    Assert.assertEquals("color:grön", productResultDto.getData().get(0).getProperties());
    Assert.assertEquals("277.00", productResultDto.getData().get(0).getPrice().toString());
    Assert.assertEquals("Blake gränden, Karlskrona", productResultDto.getData().get(0).getStoreAddress());
  }

  @Test
  public void testGetProductsEmpty(){
    ProductSearchDto filter = new ProductSearchDto();
    List<Product> products = new ArrayList<>();

    when( template.query(any(String.class), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto response = productSearchService.getProducts(filter);
    Assert.assertNull(response);
  }

}
