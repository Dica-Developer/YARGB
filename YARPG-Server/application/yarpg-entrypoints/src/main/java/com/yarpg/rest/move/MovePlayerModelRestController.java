package com.yarpg.rest.move;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.User;
import com.yarpg.core.usecase.move.MovePlayerModelUseCase;
import com.yarpg.rest.YarpgUris;

@RestController
public class MovePlayerModelRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovePlayerModelRestController.class);

    private MovePlayerModelUseCase _movePlayerModelUseCase;

    public MovePlayerModelRestController(MovePlayerModelUseCase movePlayerModelUseCase) {
        _movePlayerModelUseCase = movePlayerModelUseCase;
    }

    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    @Path(YarpgUris.UPDATE_PLAYER_LOCATION)
    public Response updatePlayerLocation(GeoRef position, @Context User user) {
        try {
            _movePlayerModelUseCase.move(position, user);
        } catch (Exception e) {
            LOGGER.info("Error", user);
        }

        return Response.ok()
                .build();
    }
}
