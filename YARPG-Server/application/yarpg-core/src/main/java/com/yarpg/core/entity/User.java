package com.yarpg.core.entity;

public class User {

    private long _userId;

    private String _gamerTag;
    private String _userMail;
    public User(long userId, String gamerTag, String userMail) {
        _userId = userId;
        _gamerTag = gamerTag;
        _userMail = userMail;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (_gamerTag == null) {
            if (other._gamerTag != null)
                return false;
        } else if (!_gamerTag.equals(other._gamerTag))
            return false;
        if (_userId != other._userId)
            return false;
        if (_userMail == null) {
            if (other._userMail != null)
                return false;
        } else if (!_userMail.equals(other._userMail))
            return false;
        return true;
    }

    public String getGamerTag() {
        return _gamerTag;
    }

    public String getMail() {
        return _userMail;
    }

    public long getUserId() {
        return _userId;
    }
}
