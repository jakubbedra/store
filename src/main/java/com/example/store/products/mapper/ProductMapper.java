package com.example.store.products.mapper;

import com.example.store.products.dto.ProductRequest;
import com.example.store.products.dto.ProductResponse;
import com.example.store.products.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .amount(product.getAmount())
                .build();
    }

    public Product toEntity(ProductRequest productResponse) {
        return Product.builder()
                .name(productResponse.getName())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .amount(productResponse.getAmount())
                .discount(0.0)
                .build();
    }

}