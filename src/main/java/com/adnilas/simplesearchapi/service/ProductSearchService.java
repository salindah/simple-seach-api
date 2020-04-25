package com.adnilas.simplesearchapi.service;

import com.adnilas.simplesearchapi.dto.ProductResultDto;
import com.adnilas.simplesearchapi.dto.ProductSearchDto;

public interface ProductSearchService {

  public ProductResultDto getProducts( ProductSearchDto filter);
}
