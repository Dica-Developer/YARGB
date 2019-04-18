package com.yarpg.core.usecase.move;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.core.exception.Messages;
import com.yarpg.core.exception.PlayerModelNotFoundException;
import com.yarpg.core.util.Maths;

public class MovePlayerModelUseCase {
    private PlayerModelProvider _playerModelProvider;
    private MapElementsProvider _mapElementsProvider;

    public MovePlayerModelUseCase(PlayerModelProvider playerModel, MapElementsProvider mapElements) {
        _playerModelProvider = playerModel;
        _mapElementsProvider = mapElements;
    }

    public void move(GeoRef newPosition, User user) {
        Optional<PlayerModel> playerModel = _playerModelProvider.getPlayerModel(user);
        if (!playerModel.isPresent()) {
            throw new PlayerModelNotFoundException(
                    String.format(Messages.PLAYER_MODEL_FOR_USER_NOT_FOUND, user.getGamerTag()));
        }

        if (validateMovement(newPosition, playerModel.get()
                .getPosition())) {
            playerModel = _playerModelProvider.setPlayerModelPosition(playerModel.get(), newPosition);
            List<MapElement> mapChunk = _mapElementsProvider.getMapChunk(playerModel.get()
                    .getPosition());
            // to handle events on the new location
        } else {
            // penalty for moving to fast
        }

        // return RESULT?
    }

    private boolean validateMovement(GeoRef newPosition, GeoRef position) {
        double distanceInMeters = Maths.distanceInMeters(position, newPosition);
        long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(newPosition.getLastChange() - position.getLastChange());
        double velocity = distanceInMeters / timeInSeconds;
        if (velocity > 50 && distanceInMeters > 100) {
            return false;
        }
        return true;
    }
}
