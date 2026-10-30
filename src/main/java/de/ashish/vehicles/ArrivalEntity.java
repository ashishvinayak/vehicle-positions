package de.ashish.vehicles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class ArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vehicleId;
    private String stopId;
    private Instant arrivalTime;

    protected ArrivalEntity() {
    }

    public ArrivalEntity(String vehicleId, String stopId, Instant arrivalTime) {
        this.vehicleId = vehicleId;
        this.stopId = stopId;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getStopId() {
        return stopId;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }
}
