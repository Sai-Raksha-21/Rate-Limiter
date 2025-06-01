package com.rateLimiter.limiter;

import com.rateLimiter.config.RateLimitConfig;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String, RateLimiter> userApiLimiterMap = new ConcurrentHashMap<>();
    private final RateLimitConfig config;

    public RateLimiterService(RateLimitConfig config) {
        this.config = config;
    }

    public boolean isAllowed(String userId, String api) {
        String key = userId + ":" + api;
        int limit = config.getLimits().getOrDefault(api, config.getDefaultLimit());
        RateLimiter limiter = userApiLimiterMap.computeIfAbsent(key,
                k -> new RateLimiter(limit, config.getWindowInSeconds() * 1000L));
        return limiter.allowRequest();
    }
}
