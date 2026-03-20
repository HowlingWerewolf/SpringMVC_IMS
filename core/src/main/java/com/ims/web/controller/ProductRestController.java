package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import com.ims.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductRestController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        log.info("REST API: Fetching all products via /api/products");
        try {
            final List<ProductDTO> products = productService.getProducts().stream().map(productMapper::map).toList();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error fetching products", e);
            return ResponseEntity.status(500).build();
        }
    }

}

