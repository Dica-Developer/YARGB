package com.yarpg.rest.dto;

import com.yarpg.core.entity.GeoRef;

public class GeoRefDTO {

    private double latitude;

    private double longitude;

    public GeoRefDTO() {

    }

    public GeoRefDTO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public static GeoRefDTO fromGeoRef(GeoRef geoRef) {
        return new GeoRefDTO(geoRef.getLatitude(), geoRef.getLongitude());
    }

    public GeoRef toGeoRef() {
        return new GeoRef(this.latitude, this.longitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
