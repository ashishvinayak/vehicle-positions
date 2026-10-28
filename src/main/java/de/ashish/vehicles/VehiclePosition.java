package de.ashish.vehicles;

import java.time.Instant;

public record VehiclePosition(String vehicleId, double latitude, double longitude, Instant timestamp) {
}
