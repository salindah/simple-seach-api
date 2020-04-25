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
    System.out.println(products.size());
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
      query.append("AND PRODUCT_TYPE = '" + filter.getType() + "'");
    }
    if( filter.getMinPrice() != null){
      query.append("AND PRICE >= " + filter.getMinPrice() );
    }
    return query.toString();
  }
}
