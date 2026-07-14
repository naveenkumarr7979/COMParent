package com.microservices.productservice.mapper;

import com.microservices.productservice.dto.CreateProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.dto.UpdateProductRequest;
import com.microservices.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public void updateEntity(Product product,
                             UpdateProductRequest request){

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

    }
    public Product toEntity(CreateProductRequest request){

        return Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

    }

    public ProductResponse toResponse(Product product){

        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

    }

}