package com.yarpg.core.entity;

public class MonsterEncounter extends MapElement {

    private String _monsterName;

    public MonsterEncounter(String monsterName, GeoRef position) {
        super(position);
        _monsterName = monsterName;
    }

    @Override
    public void checkForAutomaticActivation(PlayerModel playerModel) {
        // MonsterEncounter are activated by the player, nothing todo there
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MonsterEncounter other = (MonsterEncounter) obj;
        if (_monsterName == null) {
            if (other._monsterName != null)
                return false;
        } else if (!_monsterName.equals(other._monsterName))
            return false;
        if (this.getPosition() == null) {
            if (other.getPosition() != null)
                return false;
        } else if (!this.getPosition()
                .equals(other.getPosition()))
            return false;

        return true;
    }

    @Override
    public String getElementType() {
        return this.getClass()
                .getName();
    }

    public String getMonsterName() {
        return _monsterName;
    }

    @Override
    public String toString() {
        return "MonsterEncounter [_monsterName=" + _monsterName + ", getElementType()=" + getElementType()
                + ", getPosition()=" + getPosition() + ", getIsActive()=" + getIsActive() + "]";
    }
}
