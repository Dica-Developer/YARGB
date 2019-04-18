package com.yarpg.core.entity;

public abstract class MapElement {

    private GeoRef _position;

    public GeoRef getPosition() {
        return _position;
    }

    public abstract void activateElement();
}
