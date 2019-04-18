package com.yarpg.rest.move;

import static com.cedarsoftware.util.io.JsonWriter.formatJson;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.googlecode.yatspec.junit.LinkingNote;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.yarpg.at.yatspec.EndToEndYatspecTest;
import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.PlayerModel;
import com.yarpg.core.entity.User;
import com.yarpg.dataprovider.jdbc.PlayerModelProviderImpl;
import com.yarpg.rest.YarpgUris;

@LinkingNote(message = "End to End test: %s", links = { MovePlayerModelRestControllerAcceptanceTest.class })
public class MovePlayerModelRestControllerAcceptanceTest extends EndToEndYatspecTest {

    @Autowired
    PlayerModelProviderImpl _playerModelProviderImpl;

    @Test
    public void updatePlayerLocation() throws UnirestException {

        GeoRef position = new GeoRef(50, 15, 0, 0);
        User user = new User(1, "name", "mail");
        PlayerModel playerModel = new PlayerModel(position, user);
        // setup
        _playerModelProviderImpl.insertPlayerModelAndUser(playerModel);

        String apiUrl = "http://localhost:8080" + YarpgUris.UPDATE_PLAYER_LOCATION;

        log("API Url", apiUrl);

        HttpResponse<String> httpResponse = Unirest.put(apiUrl)
                .body(new Gson().toJson(position))
                .asString();

        int responseStatus = httpResponse.getStatus();
        log("Response Status", responseStatus);

        String responseContent = httpResponse.getBody();
        log("Response Content", formatJson(responseContent));
    }

    @Test
    public void serverInfo() throws UnirestException {

        String apiUrl = "http://localhost:8080/server/info";

        log("API Url", apiUrl);

        HttpResponse<String> httpResponse = Unirest.get(apiUrl)
                .asString();

        int responseStatus = httpResponse.getStatus();
        log("Response Status", responseStatus);

        String responseContent = httpResponse.getBody();
        log("Response Content", formatJson(responseContent));
    }

}
