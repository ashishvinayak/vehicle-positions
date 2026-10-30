package de.ashish.vehicles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ArrivalListener {
    private static final Logger log = LoggerFactory.getLogger(ArrivalListener.class);
    private final ArrivalRepository repository;

    public ArrivalListener(ArrivalRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitConfig.ARRIVALS_QUEUE)
    void receive(ArrivalEvent arrivalEvent) {
        repository.save(new ArrivalEntity(arrivalEvent.vehicleId(), arrivalEvent.stopId(), arrivalEvent.arrivedAt()));
        log.info("Stored arrival: {}", arrivalEvent);
    }
}
