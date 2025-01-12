package com.abhishake.dynamodb.service;

import com.abhishake.dynamodb.model.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(String id);

    ProductDto createNewProduct(ProductDto dto);

    ProductDto updateProduct(String id, ProductDto dto);

    void deleteProduct(String id);
}
