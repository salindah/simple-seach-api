package com.adnilas.simplesearchapi.connector;

import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductSearchConnector {

  @Autowired
  ProductSearchService productSearchService;

  @GetMapping("/test")
  public String testHealth(){
    System.out.println("OK");
    return "Product search service is running!";
  }

  @GetMapping
  public ProductResultDto getProducts(){

    ProductSearchDto searchDto = new ProductSearchDto();
    return productSearchService.getProducts(searchDto);
  }
}
