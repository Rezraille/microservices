package ru.aston.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/user-fallback")
    public ResponseEntity<String> userFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("User-Service временно недоступен. Попробуйте позже.");
    }

    @GetMapping("/mail-fallback")
    public ResponseEntity<String> mailFallback() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Mail-Service временно недоступен. Попробуйте позже.");
    }
}
