package com.yarpg.it.database;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yarpg.application.configuration.dataprovider.DataProviderConfiguration;
import com.yarpg.application.configuration.dataprovider.DatasourceConfiguration;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;

@ContextConfiguration(classes = { DatasourceConfiguration.class, DataProviderConfiguration.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected void cleanUpDatabase() {
        jdbcTemplate.update("DELETE FROM YARPG.PLAYER_MODEL");
        jdbcTemplate.update("DELETE FROM YARPG.USER");
        jdbcTemplate.update("DELETE FROM YARPG.MAP_ELEMENT");
    }

    protected void createPlayerModel(PlayerModel playerModel) {
        jdbcTemplate.update(
                "INSERT INTO YARPG.PLAYER_MODEL (USER_ID, LATITUDE, LONGITUDE, LAST_CHANGED) VALUES (?, ?, ?, ?);",
                playerModel.getUser()
                        .getUserId(),
                playerModel.getPosition()
                        .getLatitude(),
                playerModel.getPosition()
                        .getLongitude(),
                playerModel.getPosition()
                        .getLastChange());
    }

    protected void createUser(User user) {
        jdbcTemplate.update("INSERT INTO YARPG.USER (USER_ID, GAME_TAG, MAIL) VALUES (?, ?, ?);", user.getUserId(),
                user.getGamerTag(), user.getMail());
    }

    protected void createPlayerModelAndUser(PlayerModel playerModel) {
        createUser(playerModel.getUser());
        createPlayerModel(playerModel);
    }
}
