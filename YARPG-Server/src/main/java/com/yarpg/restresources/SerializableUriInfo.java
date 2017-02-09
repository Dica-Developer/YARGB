package com.yarpg.restresources;

import java.io.Serializable;
import java.net.URI;

public class SerializableUriInfo implements Serializable {
	protected final URI _resourcePath;

	protected SerializableUriInfo(final URI resourcePath) {
		_resourcePath = resourcePath;
	}

	public URI currentRelativUri() {
		return _resourcePath;
	}
}
