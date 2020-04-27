package com.adnilas.simplesearchapi.service.impl;

import com.adnilas.simplesearchapi.dto.ProductDto;
import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;
import com.adnilas.simplesearchapi.model.Product;
import com.adnilas.simplesearchapi.service.ProductSearchService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

  @Autowired
  private JdbcTemplate template;

  @Override
  public ProductResultDto getProducts(ProductSearchDto filter) {

    ProductResultDto resultDto = null;
    String sql = generateQuery(filter);

    List<Product> products = template.query(sql, new BeanPropertyRowMapper<>(Product.class));
    if (!products.isEmpty()) {
      resultDto = new ProductResultDto();
      List<ProductDto> dtoList = products.stream()
          .map(product -> product.toDto())
          .collect(Collectors.toList());
      resultDto.setData(dtoList);
    }
    return resultDto;
  }

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
    if(filter.getMinimumLimit() != null ){
      query.append("\nAND GB_LIMIT >= " + filter.getMinimumLimit() );
    }
    if(filter.getMaximumLimit() != null ){
      query.append("\nAND GB_LIMIT <= " + filter.getMaximumLimit());
    }
    return query.toString();
  }

  public JdbcTemplate getTemplate() {
    return template;
  }

  public void setTemplate(JdbcTemplate template) {
    this.template = template;
  }
}
