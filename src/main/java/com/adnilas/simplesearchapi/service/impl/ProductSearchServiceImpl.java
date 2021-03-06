package com.adnilas.simplesearchapi.service.impl;

import com.adnilas.simplesearchapi.dto.ProductDto;
import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.model.Product;
import com.adnilas.simplesearchapi.service.ProductSearchService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * This class act as the service class which execute product search method.
 *
 * @author  Salinda Hettiarachchi
 */
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

  Logger logger = LoggerFactory.getLogger(ProductSearchServiceImpl.class);

  @Autowired
  private JdbcTemplate template;

  /**
   * This method connect to database and fetch the results according to the criteria.
   *
   * @param filter Search dto object that
   * @return Product list for the query.
   */
  @Override
  public ProductResultDto getProducts(ProductSearchDto filter) {

    ProductResultDto resultDto = null;
    String sql = generateQuery(filter);
    if(logger.isDebugEnabled()){
      logger.debug("Effective query : {}", sql);
    }
    List<Product> products = template.query(sql, new BeanPropertyRowMapper<>(Product.class));
    if (!products.isEmpty()) {
      resultDto = new ProductResultDto();

      List<ProductDto> dtoList = products.stream()
          .map(product -> product.toDto())
          .collect(Collectors.toList());
      resultDto.setData(dtoList);
      if(logger.isDebugEnabled()){
        logger.debug("Number of matching items fetched : {}", products.size());
      }
    }
    return resultDto;
  }

  /**
   * This method return a query based on the search dto object.
   *
   * @param filter Search dto object that
   * @return Query as a string.
   */
  private String generateQuery(ProductSearchDto filter) {
    StringBuilder query = new StringBuilder("SELECT * FROM PRODUCTS WHERE ID > 0 ");
    if (filter.getType() != null) {
      query.append("\nAND PRODUCT_TYPE = '" + filter.getType() + "'");
    }
    if( filter.getMinPrice() != null){
      query.append("\nAND PRICE >= " + filter.getMinPrice() );
    }
    if( filter.getMaxPrice() != null){
      query.append("\nAND PRICE <= " + filter.getMaxPrice() );
    }
    if(filter.getCity() != null ){
      query.append("\nAND CITY = '" + filter.getCity() + "'");
    }
    if(filter.getProperty() != null){
      query.append("\nAND PROPERTY = '" + filter.getProperty() + "'");
    }
    if(filter.getColor() != null ){
      query.append("\nAND COLOR = '" + filter.getColor() + "'");
    }
    if(filter.getMinimumLimit() != null || filter.getMaximumLimit() != null){
      query.append("\nAND PROPERTY = 'gb_limit'");
    }
    if(filter.getMinimumLimit() != null ){
      query.append("\nAND GB_LIMIT >= " + filter.getMinimumLimit() );
    }
    if(filter.getMaximumLimit() != null ){
      query.append("\nAND GB_LIMIT <= " + filter.getMaximumLimit());
    }
    return query.toString();
  }
}
