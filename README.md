# Microservices Phase 2 - Complete Setup

## Architecture Overview
This project contains a complete microservices architecture with:

### Core Services
1. **Service Registry** (Port 8761) - Eureka Server for service discovery
2. **Config Server** (Port 8888) - Centralized configuration management
3. **Cloud Gateway** (Port 8080) - API Gateway with load balancing and circuit breaker
4. **Order Service** (Port 8081) - Business service for order management
5. **Payment Service** (Port 8082) - Business service for payment processing
6. **Hystrix Dashboard** (Port 9411) - Monitoring and metrics dashboard

## Key Features
- ✅ **Service Discovery** with Eureka
- ✅ **Centralized Configuration** from GitHub repository
- ✅ **API Gateway** with Spring Cloud Gateway
- ✅ **Load Balancing** with Spring Cloud LoadBalancer
- ✅ **Circuit Breaker** with Resilience4j
- ✅ **Monitoring Dashboard** with Actuator endpoints
- ✅ **Database Integration** with H2 (file-based for persistence)

## Configuration Repository
External configuration is managed through GitHub repository:
`https://github.com/Yashbhawsar27/cloud-config-server.git`

## Startup Order
1. **Service Registry** (8761) - Must start first
2. **Config Server** (8888) - Second priority
3. **Order Service** (8081) - Business services
4. **Payment Service** (8082) - Business services
5. **Cloud Gateway** (8080) - API Gateway
6. **Hystrix Dashboard** (9411) - Monitoring

## API Endpoints
- **Gateway**: http://localhost:8080
- **Order Service**: http://localhost:8080/order/**
- **Payment Service**: http://localhost:8080/payment/**
- **Eureka Dashboard**: http://localhost:8761
- **Config Server**: http://localhost:8888
- **Monitoring**: http://localhost:9411

## Technology Stack
- Spring Boot 3.5.7
- Spring Cloud 2023.0.3
- Java 17
- H2 Database
- Maven
- Eureka, Gateway, Config Server, Resilience4j

## Load Balancing
- Gateway level: `lb://service-name` protocol
- Service level: `@LoadBalanced RestTemplate`
- Multiple instances supported automatically

## Circuit Breaker
- Configured for both order-service and payment-service
- Fallback endpoints available at `/fallback/*`
- Resilience4j configuration in gateway and services