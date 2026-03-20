package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.form.PriceIncrease;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class PriceIncreaseFormController {

    private final ProductService productService;

    @PostMapping(value = "/priceincrease")
    public ResponseEntity<Object> onSubmitApi(@RequestBody @Valid final PriceIncrease command) {
        // Validation issues will be handled by Spring's MethodArgumentNotValidException
        final int increase = command.getPercentage();
        log.info("Increasing prices by {}%.", increase);
        productService.increasePrice(increase);

        log.info("returning from PriceIncreaseForm");
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @GetMapping(value = "/priceincrease")
    public ResponseEntity<Map<String, Object>> display() {
        final PriceIncrease pi = new PriceIncrease();
        pi.setPercentage(20);
        final Map<String, Object> response = new HashMap<>();
        response.put("priceincrease", pi);
        return ResponseEntity.ok(response);
    }

    // (legacy MVC removed) use REST endpoints /api/priceincrease instead
    // (legacy MVC removed) use REST endpoints /api/priceincrease instead
    // (legacy MVC removed) use REST endpoints /api/priceincrease instead
    // (legacy MVC removed) use REST endpoints /api/priceincrease instead

}
