package com.yarpg.core.entity;

public abstract class MapElement {

    private GeoRef _position;
    private boolean _isActive = false;

    public MapElement(GeoRef position) {
        _position = position;
    }

    public abstract void checkForAutomaticActivation(PlayerModel playerModel);

    public abstract String getElementType();

    public boolean getIsActive() {
        return _isActive;
    }

    public GeoRef getPosition() {
        return _position;
    }
}
