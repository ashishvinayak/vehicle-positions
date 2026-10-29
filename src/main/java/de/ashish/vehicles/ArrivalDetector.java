package de.ashish.vehicles;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ArrivalDetector {
    private final Map<String, Boolean> insideByVehicle = new ConcurrentHashMap<>();

    /* Returns true exactly on the outside -> inside transition */
    public boolean isArrival(String vehicleId, double distanceMeters){
        Boolean wasInside = insideByVehicle.get(vehicleId);
        boolean inside = Boolean.TRUE.equals(wasInside)
                ? distanceMeters <= Geofence.EXIT_RADIUS_M
                : distanceMeters < Geofence.ENTER_RADIUS_M;
        insideByVehicle.put(vehicleId, inside);
        return Boolean.FALSE.equals(wasInside) && inside;
    }
}
