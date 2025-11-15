#!/bin/bash

echo "Building Production-Level Microservices..."

# Build all services
echo "Building Service Registry..."
cd service-registry && mvn clean package -DskipTests && cd ..

echo "Building Config Server..."
cd config-server && mvn clean package -DskipTests && cd ..

echo "Building Cloud Gateway..."
cd cloud-gateway && mvn clean package -DskipTests && cd ..

echo "Building Order Service..."
cd order-service && mvn clean package -DskipTests && cd ..

echo "Building Payment Service..."
cd payment-service && mvn clean package -DskipTests && cd ..

# Build Docker images
echo "Building Docker images..."
docker build -t microservices/service-registry:latest ./service-registry
docker build -t microservices/config-server:latest ./config-server
docker build -t microservices/cloud-gateway:latest ./cloud-gateway
docker build -t microservices/order-service:latest ./order-service
docker build -t microservices/payment-service:latest ./payment-service

echo "Starting production environment with Docker Compose..."
docker-compose up -d

echo "Deployment completed!"
echo "Access points:"
echo "- API Gateway: http://localhost:8080"
echo "- Eureka Dashboard: http://localhost:8761"
echo "- Grafana: http://localhost:3000 (admin/admin123)"
echo "- Prometheus: http://localhost:9090"
echo "- Kibana: http://localhost:5601"
echo "- RabbitMQ Management: http://localhost:15672 (admin/admin123)"