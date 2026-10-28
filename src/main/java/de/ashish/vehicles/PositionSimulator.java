package de.ashish.vehicles;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PositionSimulator {
    private static final double STOP_LAT = 52.5251;
    private static final double STOP_LONG = 13.3694;
    private static final double STEP_FRACTION = 0.02;
    private static final double NOISE_DEG = 0.00005;

    private final KafkaTemplate<String, VehiclePosition> kafka;
    private final Map<String, VehiclePosition> buses = new HashMap<>();

    public PositionSimulator(KafkaTemplate<String, VehiclePosition> kafka) {
        this.kafka = kafka;
        Instant now = Instant.now();

        buses.put("bus-1", new VehiclePosition("bus-1", 52.5450, 13.3694, now));
        buses.put("bus-2", new VehiclePosition("bus-2", 52.5251, 13.4000, now));
        buses.put("bus-3", new VehiclePosition("bus-3", 52.5100, 13.3450, now));
    }

    @Scheduled(fixedRate = 1000)
    void tick() {
        Instant now = Instant.now();
        buses.replaceAll((id, position) -> move(position, now));
        buses.values().forEach(position -> kafka.send(KafkaConfig.POSITIONS_TOPIC, position.vehicleId(), position));
    }

    private VehiclePosition move(VehiclePosition vehiclePosition, Instant now) {
        var random = ThreadLocalRandom.current();
        double lat = vehiclePosition.latitude() +  (STOP_LAT - vehiclePosition.latitude()) * STEP_FRACTION + random.nextGaussian() * NOISE_DEG;
        double lon = vehiclePosition.longitude() + (STOP_LONG - vehiclePosition.longitude()) * STEP_FRACTION + random.nextGaussian() * NOISE_DEG;
        return new VehiclePosition(vehiclePosition.vehicleId(), lat, lon, now);
    }
}
