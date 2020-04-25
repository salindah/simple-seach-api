package com.adnilas.simplesearchapi.connector;

import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.service.ProductSearchService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductSearchConnector {

  @Autowired
  ProductSearchService productSearchService;

  @GetMapping("/test")
  public String testHealth() {
    return "Product search service is running!";
  }

  @GetMapping
  @ResponseBody
  public ProductResultDto getProducts(
      @RequestParam(name = "type", required = false) String type,
      @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
      @RequestParam(name = "max_price", required = false) BigDecimal maxPrice) {

    ProductSearchDto searchDto = new ProductSearchDto();
    searchDto.setType(type);
    searchDto.setMinPrice(minPrice);
    searchDto.setMaxPrice(maxPrice);
    return productSearchService.getProducts(searchDto);
  }
}
