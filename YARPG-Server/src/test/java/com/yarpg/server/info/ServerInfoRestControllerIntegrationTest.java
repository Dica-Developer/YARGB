package com.yarpg.server.info;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import com.yarpg.restresources.YarpgUriInfo;

public class ServerInfoRestControllerIntegrationTest {
	private static String ServerInfoURL = "http://localhost:8080/" + YarpgUriInfo.SERVER_INFO;

	@Test
	public void serverInfoResponseOK() throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ServerInfoURL);
		String response = webTarget.request().get(String.class);
		System.out.println(response);
		assertThat(response, is("Hello, World!"));
	}
}
