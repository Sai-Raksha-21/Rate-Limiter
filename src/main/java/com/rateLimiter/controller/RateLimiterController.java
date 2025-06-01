package com.rateLimiter.controller;

import com.rateLimiter.limiter.RateLimiterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RateLimiterController {
    private final RateLimiterService rateLimiterService;

    public RateLimiterController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/data")
    public ResponseEntity<String> getData(@RequestParam String user) {
        return respond(user, "/api/data");
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus(@RequestParam String user) {
        return respond(user, "/api/status");
    }

    private ResponseEntity<String> respond(String user, String api) {
        if (rateLimiterService.isAllowed(user, api)) {
            return ResponseEntity.ok("Allowed: " + api);
        } else {
            return ResponseEntity.status(429).body("Rate limit exceeded");
        }
    }
}
