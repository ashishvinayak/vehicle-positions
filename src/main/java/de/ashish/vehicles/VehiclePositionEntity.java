package de.ashish.vehicles;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;

@Entity
public class VehiclePositionEntity {
    @Id
    private String vehicleId;
    private double latitude;
    private double longitude;
    private Instant timestamp;

    protected VehiclePositionEntity() {
    }

    public VehiclePositionEntity(String vehicleId, double latitude, double longitude, Instant timestamp) {
        this.vehicleId = vehicleId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
