package com.yarpg.server.info;

import java.net.URI;

public class ServerInfoService {
	public static ServerInfoResource getServerInformations(URI self) {
		return new ServerInfoResource(self, 0, 0, 1);
	}
}
