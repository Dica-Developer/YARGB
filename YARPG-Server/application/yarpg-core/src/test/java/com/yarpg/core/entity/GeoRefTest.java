package com.yarpg.core.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GeoRefTest {

    @Test
    public void distanceInMetersTest_Longtitude() {
        GeoRef p1 = new GeoRef(0, 0, 0, 0);
        GeoRef p2 = new GeoRef(0, 15, 0, 0);
        double distanceInMeters = GeoRef.distanceInMeters(p1, p2);
        assertThat(Math.round(distanceInMeters * 10) / 10D).isEqualTo(1667923.9);
    }

    @Test
    public void distanceInMetersTest_Latitude() {
        GeoRef p1 = new GeoRef(0, 0, 0, 0);
        GeoRef p2 = new GeoRef(15, 0, 0, 0);
        double distanceInMeters = GeoRef.distanceInMeters(p1, p2);
        assertThat(Math.round(distanceInMeters * 10) / 10D).isEqualTo(1667923.9);
    }

    @Test
    public void distanceInMetersTest() {
        GeoRef p1 = new GeoRef(0, 0, 0, 0);
        GeoRef p2 = new GeoRef(15, 15, 0, 0);
        double distanceInMeters = GeoRef.distanceInMeters(p1, p2);
        assertThat(Math.round(distanceInMeters * 10) / 10D).isEqualTo(2345165.6);
    }

    @Test
    public void distanceInLonTest() {
        GeoRef p = new GeoRef(0, 0, 0, 0);
        double distanceInLon = GeoRef.distanceInLon(p, 1667923.9);
        assertThat(Math.round(distanceInLon)).isEqualTo(15);

        double distanceInLon2 = GeoRef.distanceInLon(p, 100);
        assertThat(Math.round(distanceInLon2 * 1000000) / 1000000D).isEqualTo(0.000899);
    }

    @Test
    public void distanceInLonTest_Lat() {
        GeoRef p = new GeoRef(51, 0, 0, 0);
        double distanceInLon = GeoRef.distanceInLon(p, 100);
        assertThat(Math.round(distanceInLon * 1000000) / 1000000D).isEqualTo(0.001429);

        GeoRef p2 = new GeoRef(51, 15, 0, 0);
        double distanceInLon2 = GeoRef.distanceInLon(p2, 100);
        assertThat(Math.round(distanceInLon2 * 1000000) / 1000000D).isEqualTo(0.001429);
    }

    @Test
    public void distanceInLatTest() {
        double distanceInLat = GeoRef.distanceInLat(-1667923.9);
        assertThat(Math.round(distanceInLat)).isEqualTo(-15);

        double distanceInLat2 = GeoRef.distanceInLat(100);
        assertThat(Math.round(distanceInLat2 * 1000000) / 1000000D).isEqualTo(0.000899);
    }
}
