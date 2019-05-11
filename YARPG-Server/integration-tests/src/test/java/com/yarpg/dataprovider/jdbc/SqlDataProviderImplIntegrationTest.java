package com.yarpg.dataprovider.jdbc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.MonsterEncounter;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.it.database.DatabaseIntegrationTest;

public class SqlDataProviderImplIntegrationTest extends DatabaseIntegrationTest {

    @Autowired
    SqlDataProviderImpl _sqlDataProviderImpl;

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
        PlayerModel playerModel2 = _sqlDataProviderImpl.getPlayerModel(user.getUserId())
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
        PlayerModel playerModel2 = _sqlDataProviderImpl.getPlayerModel(user)
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
        PlayerModel playerModel2 = _sqlDataProviderImpl.getPlayerModel(user.getMail())
                .get();

        // verify
        assertThat(playerModel2).isEqualTo(playerModel);
    }

    @Test
    public void getPlayerModel_byUser_NotFound() {
        // setup
        User user = new User(1, "name", "mailAddress");

        // to test
        Optional<PlayerModel> playerModel = _sqlDataProviderImpl.getPlayerModel(user);

        // verify
        assertThat(playerModel.isPresent()).isFalse();
    }

    @Test
    public void addAndGetMapElementsTest() {
        // setup
        List<MapElement> newElements = Lists.newArrayList();
        MonsterEncounter encounter1 = new MonsterEncounter("TestName", new GeoRef(50, 10, 0, 0));
        MonsterEncounter encounter2 = new MonsterEncounter("TestName2", new GeoRef(49, 11.9, 0, 0));
        MonsterEncounter encounter3 = new MonsterEncounter("Outsite", new GeoRef(10, 15, 0, 0));
        newElements.add(encounter1);
        newElements.add(encounter2);
        newElements.add(encounter3);

        // toTest
        _sqlDataProviderImpl.addMapElements(newElements);

        // setup
        GeoRef pStart = new GeoRef(50.0001, 9.99999, 0, 0);
        GeoRef pEnd = new GeoRef(48.999999, 12.000001, 0, 0);

        // toTest
        List<MapElement> mapBetween = _sqlDataProviderImpl.getMapBetween(pStart, pEnd);

        // verify
        assertThat(mapBetween).containsExactly(encounter1, encounter2);
    }

    @Test
    public void addAndDeleteMapElementsTest() {
        // setup
        List<MapElement> newElements = Lists.newArrayList();
        MonsterEncounter encounter1 = new MonsterEncounter("TestName", new GeoRef(50, 10, 0, 0));
        MonsterEncounter encounter2 = new MonsterEncounter("TestName2", new GeoRef(49, 11.9, 0, 0));
        MonsterEncounter encounter3 = new MonsterEncounter("Outsite", new GeoRef(10, 15, 0, 0));
        newElements.add(encounter1);
        newElements.add(encounter2);
        newElements.add(encounter3);
        _sqlDataProviderImpl.addMapElements(newElements);
        GeoRef pStart = new GeoRef(50.0001, 9.99999, 0, 0);
        GeoRef pEnd = new GeoRef(48.999999, 12.000001, 0, 0);
        List<MapElement> mapBetween = _sqlDataProviderImpl.getMapBetween(pStart, pEnd);
        assertThat(mapBetween).containsExactly(encounter1, encounter2);

        // to test
        _sqlDataProviderImpl.removeMapElementsBetween(pStart, pEnd);

        // verify
        List<MapElement> mapBetween2 = _sqlDataProviderImpl.getMapBetween(pStart, pEnd);
        assertThat(mapBetween2).isEmpty();
    }
}
