# Depot Tracker

A small Spring Boot project for learning Kafka and RabbitMQ. Simulated buses send their positions to Kafka. A consumer stores the latest position of each bus in PostgreSQL and publishes an arrival event to RabbitMQ when a bus reaches a stop.

```
Simulator ──► Kafka "vehicle-positions" (key = vehicleId) ──► consumer
                                                                ├──► PostgreSQL (latest position)
                                                                └──► geofence ──► RabbitMQ "vehicle-events" ──► "arrivals"
```

- **Key = vehicleId:** all positions of one bus go to the same partition, so they stay in order.
- **Kafka for the position stream, RabbitMQ for individual arrival events.**
- **Geofence with hysteresis:** inside below 50 m, outside again only above 80 m.

## Run

```bash
docker compose up -d
./mvnw spring-boot:run
```

- Health: http://localhost:8080/actuator/health
- RabbitMQ UI: http://localhost:15672 (app / app)
