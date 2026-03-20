package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import com.ims.web.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

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

    // Delete by id provided in path
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Map<String, Object>> deleteById(@PathVariable("id") final Integer id) {
        log.info("Deleting product with id {}", id);
        final var productOpt = productService.getProducts().stream().filter(p -> p.getId() == id).findFirst();
        if (productOpt.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("status", "not_found", "id", id));
        }
        productService.deleteByID(id);
        return ResponseEntity.ok(Map.of("status", "deleted", "id", id));
    }

    @PostMapping(value = "/productadd")
    public ResponseEntity<Object> onSubmitApi(@RequestBody @Valid final ProductDTO productDTO) {
        // Validation handled by framework; if invalid, Spring will return 400
        final String description = productDTO.getDescription();
        final Double price = productDTO.getPrice();

        log.info("adding to DB this product: {} with price {}", description, price);
        productService.save(productDTO);
        log.info("returning from ProductAddFormController");

        return ResponseEntity.ok(Map.of("status", "created"));
    }

}

