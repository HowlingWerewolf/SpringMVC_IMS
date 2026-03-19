package com.ims.web.api;

import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        log.info("REST API: Fetching all products");
        try {
            List<Product> products = productService.getProducts();
            if (products == null) {
                products = new ArrayList<>();
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            log.error("Error fetching products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        log.info("REST API: Adding product - description: {}, price: {}", 
                 product.getDescription(), product.getPrice());
        try {
            productService.addProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (Exception e) {
            log.error("Error adding product", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        log.info("REST API: Deleting product with id: {}", id);
        try {
            Product product = Product.builder().id(id).build();
            productService.deleteProduct(product);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting product", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

