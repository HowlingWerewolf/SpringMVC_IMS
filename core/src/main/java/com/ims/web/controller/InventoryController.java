package com.ims.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class InventoryController {

    @GetMapping(value = "/hello")
    public ResponseEntity<Map<String, Object>> handleRequest() {
        final String now = (new Date()).toString();
        log.info("returning hello JSON with {}", now);
        return ResponseEntity.ok().build();
    }

}
