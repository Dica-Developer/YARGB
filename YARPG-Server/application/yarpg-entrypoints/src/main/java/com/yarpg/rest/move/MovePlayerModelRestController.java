package com.yarpg.rest.move;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.User;
import com.yarpg.core.usecase.move.MovePlayerModelUseCase;
import com.yarpg.core.usecase.user.UserUseCase;
import com.yarpg.rest.YarpgUris;
import com.yarpg.rest.dto.GeoRefDTO;

@RestController
public class MovePlayerModelRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovePlayerModelRestController.class);

    private MovePlayerModelUseCase _movePlayerModelUseCase;

    private UserUseCase _userUseCase;

    public MovePlayerModelRestController(MovePlayerModelUseCase movePlayerModelUseCase, UserUseCase userUseCase) {
        _movePlayerModelUseCase = movePlayerModelUseCase;
        _userUseCase = userUseCase;
    }

    @RequestMapping(value = YarpgUris.UPDATE_PLAYER_LOCATION, method = GET, consumes = MediaType.APPLICATION_JSON)
    public Response updatePlayerLocation(@RequestBody GeoRefDTO position) {
        List<MapElement> move = null;
        try {
            User user = _userUseCase.getUser("defaultUserGamerTag", "");
            move = _movePlayerModelUseCase.move(position.toGeoRef(), user);
        } catch (Exception e) {
            LOGGER.info("Error", e);
            return Response.status(Status.NOT_FOUND)
                    .entity(e)
                    .build();
        }

        return Response.ok(move)
                .build();
    }

    @RequestMapping(value = "georef", method = GET)
    public GeoRefDTO getGeoref() {
        return GeoRefDTO.fromGeoRef(new GeoRef(50, 10));
    }

    @RequestMapping(value = "georef2", method = GET, consumes = MediaType.APPLICATION_JSON)
    public GeoRefDTO getGeoref(@RequestBody GeoRefDTO position) {
        System.out.println(position.toGeoRef()
                .toString());
        return position;
    }
}
