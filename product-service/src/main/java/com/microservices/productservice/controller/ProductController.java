package com.microservices.productservice.controller;

import com.microservices.productservice.dto.CreateProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.dto.UpdateProductRequest;
import com.microservices.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody CreateProductRequest request){

        return productService.createProduct(request);

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);

    }
    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request){

        return productService.updateProduct(id, request);

    }
    @GetMapping
    public List<ProductResponse> getProducts(){

        return productService.getAllProducts();

    }
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id){

        return productService.getProductById(id);

    }

}
