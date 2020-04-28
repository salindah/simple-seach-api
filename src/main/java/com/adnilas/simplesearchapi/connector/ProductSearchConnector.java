package com.adnilas.simplesearchapi.connector;

import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.service.ProductSearchService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class act as an endpoint that models product search.
 *
 * @author  Salinda Hettiarachchi
 */
@RestController
@RequestMapping("/product")
public class ProductSearchConnector {

  Logger logger = LoggerFactory.getLogger(ProductSearchConnector.class);

  @Autowired
  ProductSearchService productSearchService;

  @GetMapping("/test")
  public String testHealth() {
    return "Product search service is running!";
  }

  /**
   * Act as the endpoint which accept query parameters and, execute product search method
   * and, return the results.
   *
   * @param type  Product type to search by
   * @param minPrice Minimum price of the product to search by
   * @param maxPrice  Maximum price of the product to search by
   * @param city City to search by
   * @param property Property of the product to search by. 'phone' or 'subscription'
   * @param color Color of the product to search by, if it is a phone.
   * @param minimumLimit Minimum GB limit of the product to search by, if it is a subscription
   * @param maximumLimit Maximum GB limit of the product to search by, if it is a subscription
   * @return ProductResultDto object which wraps a List of products as resulted.
   */
  @GetMapping
  @ResponseBody
  public ProductResultDto getProducts(
      @RequestParam(name = "type", required = false) String type,
      @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
      @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
      @RequestParam(name = "city", required = false) String city,
      @RequestParam(name = "property", required = false) String property,
      @RequestParam(name = "property:color", required = false) String color,
      @RequestParam(name = "property:gb_limit_min", required = false) Integer minimumLimit,
      @RequestParam(name = "property:gb_limit_max", required = false) Integer maximumLimit) {

    try{
      ProductSearchDto searchDto = new ProductSearchDto();
      searchDto.setType(type);
      searchDto.setMinPrice(minPrice);
      searchDto.setMaxPrice(maxPrice);
      searchDto.setCity(city);
      searchDto.setProperty(property);
      searchDto.setColor(color);
      searchDto.setMinimumLimit(minimumLimit);
      searchDto.setMaximumLimit(maximumLimit);

      if(logger.isDebugEnabled()){
        logger.debug("Search criteria: {}", searchDto.toString());
      }
      return productSearchService.getProducts(searchDto);
    } catch (Exception ex) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while searching products.", ex);
    }
  }
}
