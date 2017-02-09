package com.yarpg.restresources;

import java.net.URI;

public class BaseHalResource implements HalResource {
	@HalLink(name = "self")
	private final URI _self;
	public BaseHalResource(URI self) {
		_self = self;
	}
}
