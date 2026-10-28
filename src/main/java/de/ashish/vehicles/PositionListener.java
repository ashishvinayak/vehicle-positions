package de.ashish.vehicles;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PositionListener {
    private static final Logger log = LoggerFactory.getLogger(PositionListener.class);
    private final VehiclePositionRepository repository;

    public PositionListener(VehiclePositionRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = KafkaConfig.POSITIONS_TOPIC)
    void receive(ConsumerRecord<String, VehiclePosition> record) {
        VehiclePosition vehiclePosition = record.value();
        repository.save(new VehiclePositionEntity(vehiclePosition.vehicleId(), vehiclePosition.latitude(),
                vehiclePosition.longitude(), vehiclePosition.timestamp()));
        log.info("vehicle-id = {}, partition = {}, offset = {}, lat = {}, long = {}, time = {}",
                vehiclePosition.vehicleId(),
                record.partition(), record.offset(), vehiclePosition.latitude(),
                vehiclePosition.longitude(), vehiclePosition.timestamp());
    }
}
