@echo off
echo Starting Microservices in correct order...

echo.
echo 1. Starting Service Registry (Eureka Server)...
cd service-registry
start "Service Registry" cmd /k "mvn spring-boot:run"
timeout /t 30

echo.
echo 2. Starting Config Server...
cd ..\config-server
start "Config Server" cmd /k "mvn spring-boot:run"
timeout /t 20

echo.
echo 3. Starting Order Service...
cd ..\order-service
start "Order Service" cmd /k "mvn spring-boot:run"
timeout /t 15

echo.
echo 4. Starting Payment Service...
cd ..\payment-service
start "Payment Service" cmd /k "mvn spring-boot:run"
timeout /t 15

echo.
echo 5. Starting Cloud Gateway...
cd ..\cloud-gateway
start "Cloud Gateway" cmd /k "mvn spring-boot:run"
timeout /t 15

echo.
echo 6. Starting Hystrix Dashboard...
cd ..\hystrix-dashboard
start "Hystrix Dashboard" cmd /k "mvn spring-boot:run"

echo.
echo All services are starting up...
echo Check the following URLs:
echo - Eureka Dashboard: http://localhost:8761
echo - Config Server: http://localhost:8888
echo - API Gateway: http://localhost:8080
echo - Order Service: http://localhost:8080/order/
echo - Payment Service: http://localhost:8080/payment/
echo - Monitoring Dashboard: http://localhost:9411
echo.
pause