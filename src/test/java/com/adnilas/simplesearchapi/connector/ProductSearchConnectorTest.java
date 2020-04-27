package com.adnilas.simplesearchapi.connector;

import com.adnilas.simplesearchapi.dto.ProductDto;
import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.service.ProductSearchService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class ProductSearchConnectorTest extends Mockito {

  @Mock
  ProductSearchService productSearchService;

  @InjectMocks
  ProductSearchConnector connector;

  @Before
  public void setup() {
    connector = new ProductSearchConnector();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetProducts(){

    ProductResultDto productResultDto = new ProductResultDto();
    ProductDto productDto = new ProductDto();
    productDto.setType("phone");
    productDto.setProperties("color:guld");
    productDto.setPrice(new BigDecimal("45.00"));
    productDto.setStoreAddress("Gustafsson gärdet, Malmö");

    List<ProductDto> dtoList = Arrays.asList(productDto);
    productResultDto.setData(dtoList);

    when(productSearchService.getProducts(any(ProductSearchDto.class)))
        .thenReturn(productResultDto);

    String type = "phone";
    BigDecimal minPrice = new BigDecimal("40.00");
    BigDecimal maxPrice = new BigDecimal("100.00");
    String city = "Malmö";
    String property = "color";
    String color = "guld";
    Integer minimumLimit = new Integer(10);
    Integer maximumLimit = new Integer(50);
    ProductResultDto response = connector.getProducts( type, minPrice,
        maxPrice, city, property, color, minimumLimit, maximumLimit);

    Assert.assertNotNull(response);
    ArgumentCaptor<ProductSearchDto> arg = ArgumentCaptor.forClass(ProductSearchDto.class);
    verify(productSearchService).getProducts(arg.capture());

    Assert.assertEquals(type, arg.getValue().getType());
    Assert.assertEquals(minPrice, arg.getValue().getMinPrice());
    Assert.assertEquals(maxPrice, arg.getValue().getMaxPrice());
    Assert.assertEquals(city, arg.getValue().getCity());
    Assert.assertEquals(property, arg.getValue().getProperty());
    Assert.assertEquals(color, arg.getValue().getColor());
    Assert.assertEquals(minimumLimit, arg.getValue().getMinimumLimit());
    Assert.assertEquals(maximumLimit, arg.getValue().getMaximumLimit());
  }

  @Test( expected = ResponseStatusException.class)
  public void testGetProductsError(){
    when(productSearchService.getProducts(any(ProductSearchDto.class)))
        .thenThrow();

    ProductResultDto response = connector.getProducts( "phone", null,
        null, null, null, null, null, null);
  }
}
