
# Spring Boot Rate Limiter

A simple token bucket-based rate limiter implemented using Spring Boot.

## Features

- ✅ Configurable rate limits per user  
- ✅ In-memory cache (`ConcurrentHashMap`) for storing request counters  
- ✅ Global or per-user rate limiting  
- ✅ Easily extendable and production-ready structure  

## Configuration

Add the following to your `application.yml`:

```yaml
rate-limiter:
  limit: 5
  refreshIntervalInSeconds: 60
```

- **limit:** Maximum allowed requests within the interval (e.g., 5 requests)  
- **refreshIntervalInSeconds:** Time window for rate limit reset (e.g., 60 seconds)  

## How to Run

Clone the repository:

```bash
git clone https://github.com/Sai-Raksha-21/Rate-Limiter.git
cd Rate-Limiter
```

Build the project using Maven:

```bash
mvn clean install
```

Run the Spring Boot application:

```bash
mvn spring-boot:run
```

## Testing the Rate Limiter

You can test the API using Postman, curl, or PowerShell:

```bash
curl "http://localhost:8080/api/data?user=testuser"
```

Expected behavior:

- You can send up to 5 requests per minute for a given user.  
- On the 6th request within 60 seconds, you will receive:

```json
{
  "error": "Rate limit exceeded"
}
```

PowerShell test example:

```powershell
for ($i = 1; $i -le 10; $i++) {
    Invoke-WebRequest -Uri "http://localhost:8080/api/data?user=testuser"
}
```

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── rateLimiter/
│               ├── config/              # Configuration class
│               │   └── RateLimitConfig.java
│               ├── controller/          # REST controller
│               │   └── RateLimitController.java
│               ├── limiter/             # Core rate limiter service
│               │   └── RateLimiterService.java
│               └── RateLimiterApplication.java
```

## Possible Improvements

- 🔄 Redis-based implementation for distributed systems  
- 🌐 IP-based or endpoint-specific rate limits  
- 📊 Expose metrics for Prometheus/Grafana  
- 🛠 Add unit & integration tests for critical paths  

## Author

Sai Raksha
