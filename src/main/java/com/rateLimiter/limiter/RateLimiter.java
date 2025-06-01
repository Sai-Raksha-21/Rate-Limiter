package com.rateLimiter.limiter;

import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private final int limit;
    private final long windowSizeMs;
    private long windowStart;
    private AtomicInteger requestCount;

    public RateLimiter(int limit, long windowSizeMs) {
        this.limit = limit;
        this.windowSizeMs = windowSizeMs;
        this.windowStart = System.currentTimeMillis();
        this.requestCount = new AtomicInteger(0);
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (now - windowStart >= windowSizeMs) {
            windowStart = now;
            requestCount.set(0);
        }
        return requestCount.incrementAndGet() <= limit;
    }
}
