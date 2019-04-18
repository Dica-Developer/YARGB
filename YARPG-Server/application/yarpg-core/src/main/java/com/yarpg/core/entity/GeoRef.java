package com.yarpg.core.entity;

public class GeoRef {
    private double _latitude;
    private double _longitude;
    private double _hight;
    private long _lastChange;

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
}
