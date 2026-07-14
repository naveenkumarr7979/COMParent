package com.microservices.productservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String productName;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private String category;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(nullable=false)
    private Integer quantity;

}