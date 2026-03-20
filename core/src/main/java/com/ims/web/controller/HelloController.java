package com.ims.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
public class HelloController {

    @GetMapping(value = "/api/hello")
    public ResponseEntity<Map<String, String>> greet() {
        try {
            final String now = (new Date()).toString();
            log.info("Returning hello JSON at {}", now);
            return ResponseEntity.ok(Map.of("now", now, "message", "hello"));
        } catch (final Exception e) {
            log.error("Error occured handling /api/hello", e);
            return ResponseEntity.status(500).body(Map.of("error", "internal error"));
        }
    }

}