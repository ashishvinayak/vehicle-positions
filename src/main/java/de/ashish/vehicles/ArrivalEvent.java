package de.ashish.vehicles;

import java.time.Instant;

public record ArrivalEvent (String vehicleId, String stopId, Instant arrivedAt){
}
