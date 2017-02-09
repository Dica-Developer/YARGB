package com.yarpg.restresources;

import java.net.URI;

public final class YarpgUriInfo extends SerializableUriInfo {

	private static final long serialVersionUID = -6062523969064928348L;
	public static final String SERVER_INFO = "/server-info";

	private YarpgUriInfo(URI resourcePath) {
		super(resourcePath);
	}
}
