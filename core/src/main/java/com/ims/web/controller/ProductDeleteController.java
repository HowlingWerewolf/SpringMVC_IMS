package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductDeleteController {

    private final ProductService productService;

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

    // Delete by sending full product object in body
    @DeleteMapping("/product")
    public ResponseEntity<Map<String, Object>> deleteByBody(@RequestBody final ProductDTO productDTO) {
        final int id = productDTO.getId();
        log.info("Deleting product from body with id {}", id);
        productService.delete(productDTO);
        return ResponseEntity.ok(Map.of("status", "deleted", "id", id));
    }

    @GetMapping(value = "/productdelete")
    public ResponseEntity<Map<String, Object>> list() {
        final Map<String, Object> myModel = new HashMap<>();
        myModel.put("now", (new java.util.Date()).toString());
        myModel.put("products", productService.getProducts());
        return ResponseEntity.ok(myModel);
    }


}
