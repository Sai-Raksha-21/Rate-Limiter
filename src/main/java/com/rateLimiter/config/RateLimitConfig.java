package com.rateLimiter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "ratelimit")
@Primary
public class RateLimitConfig {
    private int defaultLimit;
    private int windowInSeconds;
    private Map<String, Integer> limits;

    public int getDefaultLimit() { return defaultLimit; }
    public void setDefaultLimit(int defaultLimit) { this.defaultLimit = defaultLimit; }

    public int getWindowInSeconds() { return windowInSeconds; }
    public void setWindowInSeconds(int windowInSeconds) { this.windowInSeconds = windowInSeconds; }

    public Map<String, Integer> getLimits() { return limits; }
    public void setLimits(Map<String, Integer> limits) { this.limits = limits; }
}
