# Microservices with Zipkin + ELK Stack

## Architecture Overview
Simple microservices setup for learning distributed tracing and centralized logging:

### Core Services
1. **Service Registry** (Port 8761) - Eureka Server
2. **Config Server** (Port 8888) - Configuration management (Local + Git)
3. **Zipkin Server** (Port 9411) - **Distributed Tracing**
4. **Order Service** (Port 8081) - Order management
5. **Payment Service** (Port 8082) - Payment processing
6. **Cloud Gateway** (Port 8080) - API Gateway
7. **Monitoring Dashboard** (Port 9090) - Simple monitoring

### Observability Stack
- **Zipkin + Sleuth** - Distributed tracing
- **ELK Stack** - Centralized logging (Elasticsearch + Logstash + Kibana)

## Key Features
- ✅ **Distributed Tracing** with Zipkin + Sleuth
- ✅ **Centralized Logging** with ELK Stack
- ✅ **Service Discovery** with Eureka
- ✅ **API Gateway** with Spring Cloud Gateway
- ✅ **Load Balancing** with Eureka
- ✅ **H2 Database** for simple data storage

## Quick Start

### 1. Start Microservices with Zipkin
```bash
start-with-zipkin-elk.bat
```

### 2. Setup ELK Stack (First Time Only)
```bash
cd elk-stack
download-elk.bat    # Download ELK components
setup-elk.bat       # Configure ELK
start-elk.bat       # Start ELK stack
```

### 3. Test Distributed Tracing
```bash
test-tracing.bat
```

## Distributed Tracing with Zipkin

### Features:
- **See request flow** between services
- **Check response times**
- **Find errors** in service calls
- **Understand service connections**

### How it Works:
1. **Sleuth** automatically adds trace/span IDs to requests
2. Each service call gets a unique span ID
3. **Zipkin** collects and stores trace data
4. **Zipkin UI** shows complete request journey

### Access:
- **Zipkin UI**: http://localhost:9411
- Click "Find Traces" to see requests
- Simple filtering options

## Centralized Logging with ELK

### Components:
- **Elasticsearch** (Port 9200) - Log storage and search
- **Logstash** - Log processing and parsing
- **Kibana** (Port 5601) - Log visualization dashboard

### Features:
- **Structured logs** with trace IDs
- **Filter logs by service**
- **View logs in real-time**
- **Connect logs with traces**

### Simple Setup:
1. Download ELK components
2. Extract files
3. Basic configuration
4. Start services

### Access:
- **Kibana**: http://localhost:5601
- Create index pattern: `microservices-logs-*`
- View and search logs

## API Endpoints

### Via Gateway (Port 8080):
```bash
# Create Order (generates trace)
POST http://localhost:8080/order/bookOrder
{
  "order": {"id":1, "name":"Laptop", "qty":1, "price":50000.0},
  "payment": {"paymentMode":"CARD", "status":"success"}
}

# Get All Orders
GET http://localhost:8080/order/all

# Get Order by ID
GET http://localhost:8080/order/1
```

## Service URLs
- **Zipkin Tracing**: http://localhost:9411
- **Kibana Logs**: http://localhost:5601
- **Elasticsearch**: http://localhost:9200
- **Service Registry**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **Order Service**: http://localhost:8081
- **Payment Service**: http://localhost:8082
- **Monitoring Dashboard**: http://localhost:9090

## Key Concepts

### Distributed Tracing:
- **Trace ID** - Track complete request
- **Span ID** - Track individual service calls
- **Request flow** - See how services communicate

### Centralized Logging:
- **Elasticsearch** - Store logs
- **Logstash** - Process logs
- **Kibana** - View logs

### Basic Observability:
- **Logs** - What happened
- **Traces** - How services talked
- **Monitoring** - System health

## Technology Stack
- Spring Boot 3.5.7
- Spring Cloud 2023.0.3
- Zipkin 3.4.0
- ELK Stack 8.11.0
- Java 17
- H2 Database
- Maven

## Learning Benefits
1. **Basic observability** concepts
2. **Local development** setup
3. **Hands-on experience** with tracing tools
4. **Microservices** communication understanding
5. **Log analysis** basics

## Configuration Management

### Local Configuration (Default):
- Uses files from `config-server/src/main/resources/config-files/`
- Immediate changes without Git commits
- Good for local development

### Git-based Configuration:
- Repository: https://github.com/Yashbhawsar27/microservices_phase_2
- Path: `cloud-config-server/`
- Run `switch-to-git-config.bat` for instructions
- Change `spring.profiles.active` to `git` in config-server

### Benefits:
- **Version Control** - Track configuration changes
- **Environment Management** - Different configs per environment
- **Remote Updates** - Update configs without redeployment
- **Team Collaboration** - Shared configuration management

Perfect for learning microservices fundamentals!