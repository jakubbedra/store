package com.example.store.products.controller;

import com.example.store.products.dto.ProductRequest;
import com.example.store.products.dto.ProductResponse;
import com.example.store.products.entity.Product;
import com.example.store.products.mapper.ProductMapper;
import com.example.store.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @Autowired
    public ProductController(
            ProductService productService,
            ProductMapper productMapper
    ) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping("api/v1/products")
    public ResponseEntity<?> saveProduct(@RequestBody ProductRequest request) {
        Product productEntity = productMapper.toEntity(request);
        productService.save(productEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("api/v1/products")
    public ResponseEntity<?> getAllProducts() {
//        List<Product> all = productService.findAll();
//        List<ProductResponse> productResponses = new ArrayList<>();
//        for (Product product : all) {
//            productResponses.add(productMapper.toResponse(product));
//        }
        List<ProductResponse> allProducts = productService.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("api/v1/products/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId) {
        Optional<Product> productOptional = productService.findById(productId);
        return productOptional
                .map(this::toResponse) // if productOptional is present
                .orElse(ResponseEntity.notFound().build()); // if no product was found
    }

    private ResponseEntity<ProductResponse> toResponse(Product product) {
        ProductResponse response = productMapper.toResponse(product);
        return ResponseEntity.ok(response);
    }

}
