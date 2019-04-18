package com.yarpg.usecase.move;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.core.exception.PlayerModelNotFoundException;
import com.yarpg.core.usecase.move.MapElementsProvider;
import com.yarpg.core.usecase.move.MovePlayerModelUseCase;
import com.yarpg.core.usecase.move.PlayerModelProvider;

public class MovePlayerModelUseCaseTest {
    private PlayerModelProvider _playerModelProvider = mock(PlayerModelProvider.class);
    private MapElementsProvider _mapElementsProvider = mock(MapElementsProvider.class);

    private MovePlayerModelUseCase _movePlayerModelUseCase = new MovePlayerModelUseCase(_playerModelProvider,
            _mapElementsProvider);

    @Test
    public void moveTest_ok() {
        GeoRef newPosition = new GeoRef(50, 15, 0, 0);
        GeoRef position = new GeoRef(50, 15, 0, 10);
        User user = new User(1, "name", "Mail");
        PlayerModel playerModel = new PlayerModel(position, user);
        when(_playerModelProvider.getPlayerModel(user)).thenReturn(Optional.of(playerModel));
        when(_playerModelProvider.setPlayerModelPosition(playerModel, newPosition))
                .thenReturn(Optional.of(playerModel));
        when(_mapElementsProvider.getMapChunk(playerModel.getPosition())).thenReturn(createMapChunk());

        _movePlayerModelUseCase.move(newPosition, user);
    }

    @Test
    public void moveTest_userNotFound() {
        GeoRef newPosition = new GeoRef(50, 15, 0, 0);
        User user = new User(1, "name", "Mail");
        when(_playerModelProvider.getPlayerModel(user)).thenReturn(null);

        assertThatExceptionOfType(PlayerModelNotFoundException.class)
                .isThrownBy(() -> _movePlayerModelUseCase.move(newPosition, user));
    }

    private List<MapElement> createMapChunk() {
        // TODO Auto-generated method stub
        return null;
    }
}
