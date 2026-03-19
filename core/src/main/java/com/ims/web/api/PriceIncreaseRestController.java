package com.ims.web.api;

import com.ims.service.ProductService;
import com.ims.web.form.PriceIncrease;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/priceincrease")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PriceIncreaseRestController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> increasePrice(@RequestBody PriceIncrease priceIncrease) {
        log.info("REST API: Increasing prices by {}%", priceIncrease.getPercentage());
        try {
            if (priceIncrease.getPercentage() <= 0 || priceIncrease.getPercentage() > 50) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Percentage must be between 1 and 50");
            }
            productService.increasePrice(priceIncrease.getPercentage());
            return ResponseEntity.ok("Prices increased successfully");
        } catch (Exception e) {
            log.error("Error increasing prices", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error increasing prices: " + e.getMessage());
        }
    }
}

