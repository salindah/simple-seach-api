package com.adnilas.simplesearchapi.service;

import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.model.Product;
import com.adnilas.simplesearchapi.service.impl.ProductSearchServiceImpl;
import java.math.BigDecimal;
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

public class ProductSearchServiceImplIntegrationTest extends Mockito {

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
  public void testGetProductsByType(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setType("phone");

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);
    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND PRODUCT_TYPE = 'phone'"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("phone", productResultDto.getData().get(0).getType());
  }


  @Test
  public void testGetProductsByMinimumPrice(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setMinPrice(new BigDecimal("200.00"));

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);
    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND PRICE >= 200.00"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("277.00", productResultDto.getData().get(0).getPrice().toString());
  }

  @Test
  public void testGetProductsByMaximumPrice(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setMaxPrice(new BigDecimal("300.00"));

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("300.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);
    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND PRICE <= 300.00"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("300.00", productResultDto.getData().get(0).getPrice().toString());
  }

  @Test
  public void testGetProductsByCity(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setCity("Karlskrona");

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);
    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND CITY = 'Karlskrona'"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("Blake gränden, Karlskrona", productResultDto.getData().get(0).getStoreAddress());
  }

  @Test
  public void testGetProductsByProperty(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setProperty("color");

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);
    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND PROPERTY = 'color'"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("color:grön", productResultDto.getData().get(0).getProperties());
  }

  @Test
  public void testGetProductsByColor(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setColor("grön");

    Product product = new Product();
    product.setProductType("phone");
    product.setProperty("color");
    product.setColor("grön");
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);
    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND COLOR = 'grön'"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("color:grön", productResultDto.getData().get(0).getProperties());
  }


  @Test
  public void testGetProductsByMinimumLimit(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setMinimumLimit(20);

    Product product = new Product();
    product.setProductType("subscription");
    product.setProperty("gb_limit");
    product.setGbLimit(30);
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);

    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND GB_LIMIT >= 20"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("gb_limit:30", productResultDto.getData().get(0).getProperties());
  }

  @Test
  public void testGetProductsByMaximumLimit(){
    ProductSearchDto filter = new ProductSearchDto();
    filter.setMaximumLimit(50);

    Product product = new Product();
    product.setProductType("subscription");
    product.setProperty("gb_limit");
    product.setGbLimit(50);
    product.setPrice(new BigDecimal("277.00"));
    product.setStreet("Blake gränden");
    product.setCity("Karlskrona");
    List<Product> products = Arrays.asList(product);

    when( template.query(eq("SELECT * FROM PRODUCTS WHERE ID > 0 \n"
        + "AND GB_LIMIT <= 50"), any(BeanPropertyRowMapper.class)))
        .thenReturn(products);

    ProductResultDto productResultDto = productSearchService.getProducts(filter);

    Assert.assertNotNull(productResultDto);
    Assert.assertNotNull(productResultDto.getData());
    Assert.assertEquals(1, productResultDto.getData().size());
    Assert.assertEquals("gb_limit:50", productResultDto.getData().get(0).getProperties());
  }
}
