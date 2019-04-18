package com.yarpg.dataprovider.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.it.database.DatabaseIntegrationTest;

public class PlayerModelProviderImplIntegrationTest extends DatabaseIntegrationTest {

    @Autowired
    PlayerModelProviderImpl _playerModelProviderImpl;

    @Before
    public void setUp() throws Exception {
        cleanUpDatabase();
    }

    @Test
    public void getPlayerModel_byId() {
        // setup
        User user = new User(1, "name", "mailAddress");
        GeoRef position = new GeoRef(50, 15, 0, 0);
        PlayerModel playerModel = new PlayerModel(position, user);
        createPlayerModelAndUser(playerModel);

        // to test
        PlayerModel playerModel2 = _playerModelProviderImpl.getPlayerModel(user.getUserId())
                .get();

        // verify
        assertThat(playerModel2).isEqualTo(playerModel);
    }

    @Test
    public void getPlayerModel_byUser() {
        // setup
        User user = new User(1, "name", "mailAddress");
        GeoRef position = new GeoRef(50, 15, 0, 0);
        PlayerModel playerModel = new PlayerModel(position, user);
        createPlayerModelAndUser(playerModel);

        // to test
        PlayerModel playerModel2 = _playerModelProviderImpl.getPlayerModel(user)
                .get();

        // verify
        assertThat(playerModel2).isEqualTo(playerModel);
    }

    @Test
    public void getPlayerModel_byMail() {
        // setup
        User user = new User(1, "name", "mailAddress");
        GeoRef position = new GeoRef(50, 15, 0, 0);
        PlayerModel playerModel = new PlayerModel(position, user);
        createPlayerModelAndUser(playerModel);

        // to test
        PlayerModel playerModel2 = _playerModelProviderImpl.getPlayerModel(user.getMail())
                .get();

        // verify
        assertThat(playerModel2).isEqualTo(playerModel);
    }

    @Test
    public void getPlayerModel_byUser_NotFound() {
        // setup
        User user = new User(1, "name", "mailAddress");

        // to test
        Optional<PlayerModel> playerModel = _playerModelProviderImpl.getPlayerModel(user);

        // verify
        assertThat(playerModel.isPresent()).isFalse();
    }
}
