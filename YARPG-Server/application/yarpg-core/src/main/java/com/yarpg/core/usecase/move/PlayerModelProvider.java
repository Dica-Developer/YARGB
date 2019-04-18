package com.yarpg.core.usecase.move;

import java.util.Optional;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;

public interface PlayerModelProvider {

    public abstract Optional<PlayerModel> getPlayerModel(String playerMail);

    public abstract Optional<PlayerModel> getPlayerModel(long playerId);

    public abstract Optional<PlayerModel> getPlayerModel(User user);

    public abstract Optional<PlayerModel> setPlayerModelPosition(PlayerModel playerModel, GeoRef position);

    public abstract void insertPlayerModelAndUser(PlayerModel playerModel);

    public abstract void insertPlayerModel(PlayerModel playerModel);

    public abstract void insertUser(User user);
}
