# Spring Boot Rate Limiter

A simple token bucket-based rate limiter implemented using Spring Boot.

## Features

- Configurable rate limits per user
- In-memory cache (ConcurrentHashMap) for storing request counters
- Global or per-user rate limiting
- Easily extendable and production-ready structure

## Configuration

`application.yml`

```yaml
rate-limiter:
  limit: 5
  refreshIntervalInSeconds: 60
