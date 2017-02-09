package com.ziroby.hello.webapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

public class HelloIntegrationTest {
	private static String HELLO_URL = "http://localhost:8080/hello";

	@Test
	public void testHello() throws Exception {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(HELLO_URL);
		String response = webTarget.request().get(String.class);
		System.out.println(response);
		assertThat(response, is("Hello, World!"));
	}
}
