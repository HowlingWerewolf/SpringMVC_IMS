package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.dto.PriceDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class PriceController {

    private final ProductService productService;
    @PostMapping(value = "/priceincrease")
    public ResponseEntity<Object> doIncrease(@RequestBody @Valid final PriceDTO priceDTO) {
        // Validation issues will be handled by Spring's MethodArgumentNotValidException
        final int increase = priceDTO.getPercentage();
        log.info("Increasing prices by {}%.", increase);
        productService.increasePrice(increase);

        log.info("returning from PriceController");
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

}
