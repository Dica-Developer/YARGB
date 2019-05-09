package com.yarpg.core.usecase.move;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.mockito.Matchers;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.MonsterEncounter;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.core.exception.PlayerModelNotFoundException;
import com.yarpg.core.exception.PlayerPenalty;
import com.yarpg.core.usecase.MapElementsProvider;
import com.yarpg.core.usecase.PlayerModelProvider;
import com.yarpg.core.usecase.move.MovePlayerModelUseCase;

public class MovePlayerModelUseCaseTest {
    private PlayerModelProvider _playerModelProvider = mock(PlayerModelProvider.class);
    private MapElementsProvider _mapElementsProvider = mock(MapElementsProvider.class);

    private MovePlayerModelUseCase _movePlayerModelUseCase = new MovePlayerModelUseCase(_playerModelProvider,
            _mapElementsProvider);

    @Test
    public void moveTest_ok() {
        // setup
        GeoRef newPosition = new GeoRef(50, 15, 0, 0);
        GeoRef position = new GeoRef(50, 15, 0, 10);
        User user = new User(1, "name", "Mail");
        PlayerModel playerModel = new PlayerModel(position, user);
        List<MapElement> createMapList = createMapList(position);

        when(_playerModelProvider.getPlayerModel(user)).thenReturn(Optional.of(playerModel));
        when(_playerModelProvider.setPlayerModelPosition(playerModel, newPosition))
                .thenReturn(Optional.of(playerModel));
        when(_mapElementsProvider.getMapBetween(Matchers.any(GeoRef.class), Matchers.any(GeoRef.class)))
                .thenReturn(createMapList);

        // to test
        List<MapElement> mapElements = null;
        try {
            mapElements = _movePlayerModelUseCase.move(newPosition, user);
        } catch (PlayerModelNotFoundException | PlayerPenalty e) {
            fail();
        }

        // verify
        assertThat(mapElements).containsOnlyElementsOf(createMapList);

    }

    @Test
    public void moveTest_userNotFound() {
        GeoRef newPosition = new GeoRef(50, 15, 0, 0);
        User user = new User(1, "name", "Mail");
        when(_playerModelProvider.getPlayerModel(user)).thenReturn(null);

        assertThatExceptionOfType(PlayerModelNotFoundException.class)
                .isThrownBy(() -> _movePlayerModelUseCase.move(newPosition, user));
    }

    private List<MapElement> createMapList(GeoRef position) {
        return Lists.newArrayList(new MonsterEncounter("name", position));
    }
}
