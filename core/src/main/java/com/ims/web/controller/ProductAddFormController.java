package com.ims.web.controller;

import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductAddFormController {

    private final ProductService productService;

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

    @GetMapping(value = "/productadd")
    public ResponseEntity<Product> display() {
        return ResponseEntity.ok(Product.builder().build());
    }

}
