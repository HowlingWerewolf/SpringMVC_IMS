package com.ims.web.controller;

import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductRestController {

    private final ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getProducts() {
        log.info("REST API: Fetching all products via /api/products");
        try {
            List<Product> products = productService.getProducts();
            if (products == null) {
                products = new ArrayList<>();
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error fetching products", e);
            return ResponseEntity.status(500).build();
        }
    }

}

