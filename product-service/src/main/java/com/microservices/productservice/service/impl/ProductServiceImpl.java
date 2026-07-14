package com.microservices.productservice.service.impl;

import com.microservices.productservice.dto.CreateProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.dto.UpdateProductRequest;
import com.microservices.productservice.entity.Product;
import com.microservices.productservice.exception.ProductNotFoundException;
import com.microservices.productservice.mapper.ProductMapper;
import com.microservices.productservice.repository.ProductRepository;
import com.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {

        log.info("Creating product : {}",request.getProductName());

        Product product=mapper.toEntity(request);

        Product savedProduct=repository.save(product);

        log.info("Product saved with id : {}",savedProduct.getId());

        return mapper.toResponse(savedProduct);

    }

    @Override
    public ProductResponse getProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));

        return mapper.toResponse(product);

    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

    @Override
    public ProductResponse updateProduct(Long id,
                                         UpdateProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"));

        mapper.updateEntity(product, request);

        Product updated = repository.save(product);

        return mapper.toResponse(updated);

    }

    @Override
    public void deleteProduct(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found"));

        repository.delete(product);

        log.info("Deleted Product {}", id);

    }

}