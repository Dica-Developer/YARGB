package com.yarpg.server.info;

import java.net.URI;

import com.yarpg.restresources.BaseHalResource;
import com.yarpg.restresources.GsonSerializable;

@GsonSerializable
public class ServerInfoResource extends BaseHalResource {

	final String _serverVersion;
	final int _major;
	final int _minor;
	final int _patch;

	public ServerInfoResource(URI self, int major, int minor, int patch) {
		super(self);
		_serverVersion = major + "." + minor + "." + patch;
		_major = major;
		_minor = minor;
		_patch = patch;
	}

}
