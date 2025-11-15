# Production-Level Microservices Architecture

## 🏗️ Architecture Overview

This is a complete production-ready microservices architecture with enterprise-grade features:

### 🔧 Core Services
- **Service Registry** (Eureka) - Service discovery and registration
- **Config Server** - Centralized configuration management
- **API Gateway** - Single entry point with routing, load balancing, and security
- **Order Service** - Business service for order management
- **Payment Service** - Business service for payment processing

### 🗄️ Data Layer
- **PostgreSQL** - Production database for each service
- **Redis** - Caching and session management
- **RabbitMQ** - Asynchronous messaging

### 📊 Monitoring & Observability
- **Prometheus** - Metrics collection
- **Grafana** - Metrics visualization and dashboards
- **ELK Stack** (Elasticsearch, Logstash, Kibana) - Centralized logging
- **Spring Boot Actuator** - Health checks and metrics

### 🛡️ Resilience Patterns
- **Circuit Breaker** - Fault tolerance with Resilience4j
- **Rate Limiting** - API throttling
- **Retry Mechanism** - Automatic retry on failures
- **Load Balancing** - Distribute traffic across instances

## 🚀 Quick Start

### Prerequisites
- Docker & Docker Compose
- Java 17
- Maven 3.6+

### 1. Build and Deploy (Windows)
```bash
build-and-deploy.bat
```

### 2. Build and Deploy (Linux/Mac)
```bash
chmod +x build-and-deploy.sh
./build-and-deploy.sh
```

### 3. Manual Docker Compose
```bash
# Build all services first
mvn clean package -DskipTests

# Start infrastructure
docker-compose up -d
```

## 🌐 Access Points

| Service | URL | Credentials |
|---------|-----|-------------|
| API Gateway | http://localhost:8080 | - |
| Eureka Dashboard | http://localhost:8761 | - |
| Grafana | http://localhost:3000 | admin/admin123 |
| Prometheus | http://localhost:9090 | - |
| Kibana | http://localhost:5601 | - |
| RabbitMQ Management | http://localhost:15672 | admin/admin123 |

## 📡 API Endpoints

### Order Service (via Gateway)
- `GET /api/orders` - Get all orders
- `POST /api/orders` - Create new order
- `GET /api/orders/{id}` - Get order by ID

### Payment Service (via Gateway)
- `GET /api/payments` - Get all payments
- `POST /api/payments` - Process payment
- `GET /api/payments/{id}` - Get payment by ID

## 🔧 Configuration Profiles

### Development
- H2 in-memory database
- Local service discovery
- Debug logging enabled

### Docker
- PostgreSQL databases
- Redis caching
- RabbitMQ messaging
- Production logging

### Kubernetes
- External databases
- ConfigMaps and Secrets
- Horizontal Pod Autoscaling

## 📊 Monitoring

### Metrics
- Application metrics via Micrometer
- JVM metrics
- Custom business metrics
- Database connection pool metrics

### Logging
- Structured JSON logging
- Centralized log aggregation
- Log correlation with trace IDs
- Error alerting

### Health Checks
- Liveness probes
- Readiness probes
- Dependency health checks

## 🛡️ Security Features

### API Gateway Security
- Rate limiting per user/IP
- Request/Response filtering
- CORS configuration
- Request size limits

### Service-to-Service
- Service discovery security
- Internal network isolation
- Database connection encryption

## 🔄 CI/CD Pipeline

### Build Pipeline
```yaml
1. Code checkout
2. Unit tests
3. Integration tests
4. Security scanning
5. Docker image build
6. Image scanning
7. Push to registry
```

### Deployment Pipeline
```yaml
1. Infrastructure provisioning
2. Database migrations
3. Service deployment
4. Health checks
5. Smoke tests
6. Traffic routing
```

## 📈 Scalability

### Horizontal Scaling
- Multiple service instances
- Load balancer configuration
- Database read replicas
- Cache clustering

### Performance Optimization
- Connection pooling
- Query optimization
- Caching strategies
- Async processing

## 🚨 Troubleshooting

### Common Issues
1. **Service not registering with Eureka**
   - Check network connectivity
   - Verify Eureka server is running
   - Check service configuration

2. **Database connection failures**
   - Verify database is running
   - Check connection strings
   - Validate credentials

3. **High memory usage**
   - Check for memory leaks
   - Tune JVM parameters
   - Monitor garbage collection

### Monitoring Commands
```bash
# Check service health
curl http://localhost:8080/actuator/health

# View metrics
curl http://localhost:8080/actuator/metrics

# Check logs
docker logs <container-name>
```

## 🔧 Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| SPRING_PROFILES_ACTIVE | Active profile | docker |
| SPRING_DATASOURCE_URL | Database URL | - |
| EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE | Eureka URL | http://service-registry:8761/eureka |
| SPRING_REDIS_HOST | Redis host | redis |
| SPRING_RABBITMQ_HOST | RabbitMQ host | rabbitmq |

## 📝 Best Practices

### Development
- Use feature branches
- Write comprehensive tests
- Follow coding standards
- Document APIs

### Operations
- Monitor all services
- Set up alerting
- Regular backups
- Security updates

### Architecture
- Loose coupling
- High cohesion
- Fail-fast design
- Graceful degradation