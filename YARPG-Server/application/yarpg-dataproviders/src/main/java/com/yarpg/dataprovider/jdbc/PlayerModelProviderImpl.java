package com.yarpg.dataprovider.jdbc;

import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.core.usecase.move.PlayerModelProvider;

public class PlayerModelProviderImpl implements PlayerModelProvider {

    private JdbcTemplate _jdbcTemplate;

    public PlayerModelProviderImpl(JdbcTemplate jdbcTemplate) {
        _jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<PlayerModel> getPlayerModel(long playerId) {
        try {
            Map<String, Object> result = _jdbcTemplate.queryForMap(
                    "Select pm.USER_ID as user, pm.LATITUDE as lat, pm.LONGITUDE as long, pm.LAST_CHANGED as lastc, u.MAIL as mail, u.GAME_TAG as gtag FROM YARPG.PLAYER_MODEL pm, YARPG.USER u WHERE pm.USER_ID = ? AND pm.USER_ID = u.USER_ID;",
                    playerId);
            return Optional.of(new PlayerModel(
                    new GeoRef((double) result.get("lat"), (double) result.get("long"), 0,
                            new Long((int) result.get("lastc"))),
                    new User(new Long((int) result.get("user")), (String) result.get("gtag"),
                            (String) result.get("mail"))));
        } catch (NullPointerException | EmptyResultDataAccessException e) {
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlayerModel> getPlayerModel(String playerMail) {
        Map<String, Object> result = _jdbcTemplate.queryForMap(
                "Select pm.USER_ID as user, pm.LATITUDE as lat, pm.LONGITUDE as long, pm.LAST_CHANGED as lastc, u.MAIL as mail, u.GAME_TAG as gtag FROM YARPG.PLAYER_MODEL pm, YARPG.USER u WHERE u.MAIL = ? AND pm.USER_ID = u.USER_ID;",
                playerMail);
        try {
            return Optional.of(new PlayerModel(
                    new GeoRef((double) result.get("lat"), (double) result.get("long"), 0,
                            new Long((int) result.get("lastc"))),
                    new User(new Long((int) result.get("user")), (String) result.get("gtag"),
                            (String) result.get("mail"))));
        } catch (NullPointerException | EmptyResultDataAccessException e) {
        }
        return Optional.empty();
    }

    @Override
    public Optional<PlayerModel> getPlayerModel(User user) {
        return getPlayerModel(user.getUserId());
    }

    @Override
    public void insertPlayerModelAndUser(PlayerModel playerModel) {
        insertUser(playerModel.getUser());
        insertPlayerModel(playerModel);
    }

    @Override
    public Optional<PlayerModel> setPlayerModelPosition(PlayerModel playerModel, GeoRef position) {
        int update = _jdbcTemplate.update(
                "UPDATE YARPG.PLAYER_MODEL SET LATITUDE = ?,LONGITUDE = ?, LAST_CHANGED = ? WHERE USER_ID = ?;",
                position.getLatitude(), position.getLongitude(), position.getLastChange(), playerModel.getUser()
                        .getUserId());
        if (update != 0) {
            playerModel.setPosition(position);
            return Optional.of(playerModel);
        }
        return Optional.empty();

        // throw new CouldNotUpdateDataException(
        // String.format(Messages.COULD_NOT_UPDATE_PLAYER_MODEL, playerModel.getUser()
        // .getGamerTag()));
    }

    @Override
    public void insertPlayerModel(PlayerModel playerModel) {
        _jdbcTemplate.update(
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

    @Override
    public void insertUser(User user) {
        _jdbcTemplate.update("INSERT INTO YARPG.USER (USER_ID, GAME_TAG, MAIL) VALUES (?, ?, ?);", user.getUserId(),
                user.getGamerTag(), user.getMail());
    }
}
