package com.yarpg.dataprovider.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.MonsterEncounter;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.core.usecase.MapElementsProvider;
import com.yarpg.core.usecase.PlayerModelProvider;
import com.yarpg.core.usecase.UserProvider;

public class SqlDataProviderImpl implements PlayerModelProvider, MapElementsProvider, UserProvider {

    private JdbcTemplate _jdbcTemplate;

    public SqlDataProviderImpl(JdbcTemplate jdbcTemplate) {
        _jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addMapElements(List<MapElement> newElements) {
        newElements.forEach(ele -> {
            if (ele instanceof MonsterEncounter) {
                MonsterEncounter monsterEncounter = (MonsterEncounter) ele;
                _jdbcTemplate.update(
                        "INSERT INTO YARPG.MAP_ELEMENT (LATITUDE, LONGITUDE, TYPE, NAME) VALUES (?, ?, ?, ?);",
                        monsterEncounter.getPosition()
                                .getLatitude(),
                        monsterEncounter.getPosition()
                                .getLongitude(),
                        monsterEncounter.getElementType(), monsterEncounter.getMonsterName());
            }
        });
    }

    @Override
    public List<MapElement> getMapBetween(GeoRef pStart, GeoRef pEnd) {
        List<Map<String, Object>> queryForList = _jdbcTemplate.queryForList(
                "Select LATITUDE as lat, LONGITUDE as long, TYPE as type, NAME as name FROM YARPG.MAP_ELEMENT WHERE (LATITUDE between ? AND ?) AND (LONGITUDE between ? AND ?);",
                pEnd.getLatitude(), pStart.getLatitude(), pStart.getLongitude(), pEnd.getLongitude());

        List<MapElement> mapList = queryForList.stream()
                .map(ele -> {
                    String type = (String) ele.get("type");
                    if (type.equals(MonsterEncounter.class.getName())) {
                        return new MonsterEncounter((String) ele.get("name"),
                                new GeoRef((double) ele.get("lat"), (double) ele.get("long"), 0, 0));
                    }
                    return null;
                })
                .collect(Collectors.toList());

        return mapList;
    }

    @Override
    public List<MapElement> getMapChunk(GeoRef position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Integer, List<MapElement>> getMapChunks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<Integer, List<MapElement>> getMapChunks(List<GeoRef> position) {
        // TODO Auto-generated method stub
        return null;
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
    public void insertPlayerModelAndUser(PlayerModel playerModel) {
        insertUser(playerModel.getUser());
        insertPlayerModel(playerModel);
    }

    @Override
    public void insertUser(User user) {
        _jdbcTemplate.update("INSERT INTO YARPG.USER (USER_ID, GAME_TAG, MAIL) VALUES (?, ?, ?);", user.getUserId(),
                user.getGamerTag(), user.getMail());
    }

    @Override
    public void moveMapElement(Map<MapElement, GeoRef> moveTo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeMapElement(List<MapElement> toRemove) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeMapElementsBetween(GeoRef pStart, GeoRef pEnd) {
        _jdbcTemplate.update("DELETE FROM MAP_ELEMENT WHERE LATITUDE between ? AND ? AND LONGITUDE between ? AND ?;",
                pStart.getLatitude(), pEnd.getLatitude(), pStart.getLongitude(), pEnd.getLongitude());
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
    }

    @Override
    public Optional<User> verifyLogin(String name, String password) {
        try {
            Map<String, Object> result = _jdbcTemplate
                    .queryForMap("Select USER_ID, MAIL, GAME_TAG FROM YARPG.USER WHERE GAME_TAG = ?;", name);

            User user = new User((int) result.get("USER_ID"), (String) result.get("GAME_TAG"),
                    (String) result.get("MAIL"));
            return Optional.of(user);

        } catch (EmptyResultDataAccessException e) {

        }
        return Optional.empty();
    }

    @Override
    public void registerUser(String name, String password) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(String name) {
        // TODO Auto-generated method stub

    }
}
