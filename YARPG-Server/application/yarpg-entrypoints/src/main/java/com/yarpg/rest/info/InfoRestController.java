package com.yarpg.rest.info;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoRestController {

    // @GET
    // @Path("/server/info")
    @RequestMapping(value = "/server/info", method = GET)
    public String updatePlayerLocation() {
        return "test";
    }
}
