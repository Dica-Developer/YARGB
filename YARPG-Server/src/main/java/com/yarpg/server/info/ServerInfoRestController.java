package com.yarpg.server.info;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.yarpg.restresources.YarpgUriInfo;
import com.yarpg.result.Result;

@Path(YarpgUriInfo.SERVER_INFO)
public class ServerInfoRestController {

	@GET
	public Response getServerInfo(@Context YarpgUriInfo self) throws URISyntaxException {
		// URI currentRelativUri = self.currentRelativUri();
		URI currentRelativUri = new URI("Name");
		Result<ServerInfoResource> entity = Result.of(ServerInfoService.getServerInformations(currentRelativUri));
		System.out.println("binda");
		return Response.ok(entity).build();
	}
}
