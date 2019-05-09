package com.yarpg.core.entity;

public class GeoRef {
    private static final int RADIUS_EARTH_IN_KM = 6371;

    private double _latitude;

    private double _longitude;

    private double _hight;

    private long _lastChange;

    public static GeoRef createLowerRightCorner(GeoRef position, int rangeToGetInMeter) {
        return new GeoRef(position.getLatitude() - distanceInLat(rangeToGetInMeter),
                position.getLongitude() + distanceInLon(position, rangeToGetInMeter));
    }

    public static GeoRef createUpperLeftCorner(GeoRef position, int rangeToGetInMeter) {
        return new GeoRef(position.getLatitude() + distanceInLat(rangeToGetInMeter),
                position.getLongitude() - distanceInLon(position, rangeToGetInMeter));
    }

    public static double distanceInLat(double meter) {
        double p2Lat = (meter / (RADIUS_EARTH_IN_KM * 1000D));
        return Math.toDegrees(p2Lat);
    }

    public static double distanceInLon(GeoRef p, double meter) {
        double p1Lat = Math.toRadians(p.getLatitude());
        double p2Longitude = (meter / (RADIUS_EARTH_IN_KM * 1000D * Math.cos(p1Lat)));
        return Math.toDegrees(p2Longitude);
    }

    public static double distanceInMeters(GeoRef p1, GeoRef p2) {
        double latDistance = Math.toRadians(p2.getLatitude() - p1.getLatitude());
        double lonDistance = Math.toRadians(p2.getLongitude() - p1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(p1.getLatitude()))
                * Math.cos(Math.toRadians(p2.getLatitude())) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = RADIUS_EARTH_IN_KM * c * 1000; // convert to meters

        double height = p1.getHight() - p2.getHight();

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public GeoRef(double latitude, double longitude) {
        _latitude = latitude;
        _longitude = longitude;
        _hight = 0;
        _lastChange = 0;
    }

    public GeoRef(double latitude, double longitude, double hight, long lastChange) {
        _latitude = latitude;
        _longitude = longitude;
        _hight = hight;
        _lastChange = lastChange;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GeoRef other = (GeoRef) obj;
        if (Double.doubleToLongBits(_hight) != Double.doubleToLongBits(other._hight))
            return false;
        if (_lastChange != other._lastChange)
            return false;
        if (Double.doubleToLongBits(_latitude) != Double.doubleToLongBits(other._latitude))
            return false;
        if (Double.doubleToLongBits(_longitude) != Double.doubleToLongBits(other._longitude))
            return false;
        return true;
    }

    public double getHight() {
        return _hight;
    }

    public long getLastChange() {
        return _lastChange;
    }

    public double getLatitude() {
        return _latitude;
    }

    public double getLongitude() {
        return _longitude;
    }

    public void setHight(double hight) {
        _hight = hight;
        _lastChange = System.currentTimeMillis();
    }

    public void setLatitude(double latitude) {
        _latitude = latitude;
        _lastChange = System.currentTimeMillis();
    }

    public void setLongitude(double longitude) {
        _longitude = longitude;
        _lastChange = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "GeoRef [_latitude=" + _latitude + ", _longitude=" + _longitude + ", _lastChange=" + _lastChange + "]";
    }
}
