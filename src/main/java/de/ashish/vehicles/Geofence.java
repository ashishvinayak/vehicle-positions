package de.ashish.vehicles;

public class Geofence {
    public static final String STOP_ID = "hbf";
    public static final double STOP_LAT = 52.5251;
    public static final double STOP_LON = 13.3694;
    public static final double ENTER_RADIUS_M = 50;
    public static final double EXIT_RADIUS_M = 80;

    public static final double EARTH_RADIUS_M = 6_371_000;

    private Geofence() {}

    public static double distanceToStopMeters(double lat, double lon){
        double dLat =  Math.toRadians(STOP_LAT - lat);
        double dLon =  Math.toRadians(STOP_LON - lon);
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(STOP_LAT))
                * Math.pow(Math.sin(dLon / 2), 2);
        return 2 * EARTH_RADIUS_M * Math.asin(Math.sqrt(a));
    }
}
