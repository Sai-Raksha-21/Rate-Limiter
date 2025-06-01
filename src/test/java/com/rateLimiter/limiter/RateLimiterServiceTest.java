package com.rateLimiter.limiter;

import com.rateLimiter.config.RateLimitConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RateLimiterServiceTest {
    private RateLimiterService service;

    @BeforeEach
    void setup() {
        RateLimitConfig config = new RateLimitConfig();
        config.setDefaultLimit(2);
        config.setWindowInSeconds(1);
        config.setLimits(Map.of("/api/test", 3));
        service = new RateLimiterService(config);
    }

    @Test
    void testLimitExceeded() {
        String user = "user1";
        assertTrue(service.isAllowed(user, "/api/test"));
        assertTrue(service.isAllowed(user, "/api/test"));
        assertTrue(service.isAllowed(user, "/api/test"));
        assertFalse(service.isAllowed(user, "/api/test"));
    }

    @Test
    void testDefaultLimit() {
        String user = "user2";
        assertTrue(service.isAllowed(user, "/api/unknown"));
        assertTrue(service.isAllowed(user, "/api/unknown"));
        assertFalse(service.isAllowed(user, "/api/unknown"));
    }
}
