package com.microservices.productservice.service;

import com.microservices.productservice.dto.CreateProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.dto.UpdateProductRequest;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long id,
                                  UpdateProductRequest request);

    void deleteProduct(Long id);

}