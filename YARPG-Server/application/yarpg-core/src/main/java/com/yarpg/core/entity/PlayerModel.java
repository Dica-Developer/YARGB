package com.yarpg.core.entity;

public class PlayerModel {
    private GeoRef _position;
    private User _user;

    public PlayerModel(GeoRef position, User user) {
        _position = position;
        _user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerModel other = (PlayerModel) obj;
        if (_position == null) {
            if (other._position != null)
                return false;
        } else if (!_position.equals(other._position))
            return false;
        if (_user == null) {
            if (other._user != null)
                return false;
        } else if (!_user.equals(other._user))
            return false;
        return true;
    }

    public GeoRef getPosition() {
        return _position;
    }

    public User getUser() {
        return _user;
    }

    public void setPosition(GeoRef position) {
        _position = position;
    }
}
