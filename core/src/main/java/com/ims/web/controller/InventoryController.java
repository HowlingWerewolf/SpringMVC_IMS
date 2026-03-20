package com.ims.web.controller;

import com.ims.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class InventoryController {

    private final ProductService productService;

    @GetMapping(value = "/hello")
    public ResponseEntity<Map<String, Object>> handleRequest() {
        final String now = (new Date()).toString();
        log.info("returning hello JSON with {}", now);

        final Map<String, Object> myModel = new HashMap<>();
        myModel.put("now", now);
        if (productService.getProducts() != null) {
            myModel.put("products", productService.getProducts());
        } else {
            myModel.put("products", new ArrayList<>());
        }
        return ResponseEntity.ok(myModel);
    }

    // (legacy MVC removed) use REST endpoint handleRequest() instead

}
