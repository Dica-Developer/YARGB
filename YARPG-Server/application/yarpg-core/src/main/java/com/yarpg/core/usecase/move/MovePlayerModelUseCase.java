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
import com.yarpg.core.exception.PlayerPenalty;
import com.yarpg.core.usecase.MapElementsProvider;
import com.yarpg.core.usecase.PlayerModelProvider;

public class MovePlayerModelUseCase {
    private PlayerModelProvider _playerModelProvider;
    private MapElementsProvider _mapElementsProvider;

    public MovePlayerModelUseCase(PlayerModelProvider playerModel, MapElementsProvider mapElements) {
        _playerModelProvider = playerModel;
        _mapElementsProvider = mapElements;
    }

    public List<MapElement> move(GeoRef newPosition, User user) throws PlayerPenalty, PlayerModelNotFoundException {
        // Optional<PlayerModel> playerModel = _playerModelProvider.getPlayerModel(user);
        Optional<PlayerModel> playerModel = _playerModelProvider.getPlayerModel(user);
        if (!playerModel.isPresent()) {
            throw new PlayerModelNotFoundException(
                    String.format(Messages.PLAYER_MODEL_FOR_USER_NOT_FOUND, user.getGamerTag()));
        }
        List<MapElement> surroundingMap;
        if (validateMovement(newPosition, playerModel.get()
                .getPosition())) {
            final PlayerModel updatedPlayerModel = _playerModelProvider
                    .setPlayerModelPosition(playerModel.get(), newPosition)
                    .get();

            GeoRef position = updatedPlayerModel.getPosition();

            int rangeToGetInMeter = 150;
            GeoRef pStart = GeoRef.createUpperLeftCorner(position, rangeToGetInMeter);
            GeoRef pEnd = GeoRef.createLowerRightCorner(position, rangeToGetInMeter);
            surroundingMap = _mapElementsProvider.getMapBetween(pStart, pEnd);
            // TODO check distance and activate
            surroundingMap.forEach(ele -> ele.checkForAutomaticActivation(updatedPlayerModel));
        } else {
            throw new PlayerPenalty(Messages.PLAYER_PENALTY_MOVING_TO_FAST);
        }

        return surroundingMap;
    }

    private boolean validateMovement(GeoRef newPosition, GeoRef position) {
        if (position.getLastChange() == 0L) {
            return true;
        }
        double distanceInMeters = GeoRef.distanceInMeters(position, newPosition);
        long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(newPosition.getLastChange() - position.getLastChange());
        double velocity = distanceInMeters / timeInSeconds;
        if (velocity > 50 && distanceInMeters > 100) {
            return false;
        }
        return true;
    }
}
