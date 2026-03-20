package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
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

    private final ProductDaoImpl productDao;

    @PostMapping(value = "/productadd")
    public ResponseEntity<Object> onSubmitApi(@RequestBody @Valid final Product command) {
        // Validation handled by framework; if invalid, Spring will return 400
        final String description = command.getDescription();
        final Double price = command.getPrice();

        log.info("adding to DB this product: {} with price {}", description, price);
        productDao.save(command);
        log.info("returning from ProductAddFormController");

        return ResponseEntity.ok(Map.of("status", "created"));
    }

    @GetMapping(value = "/productadd")
    public ResponseEntity<Product> display() {
        return ResponseEntity.ok(Product.builder().build());
    }

    // (legacy MVC removed) use REST endpoints /api/productadd instead

}
