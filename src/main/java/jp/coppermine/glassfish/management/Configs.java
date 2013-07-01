package jp.coppermine.glassfish.management;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Configs {
	private final URI uri;
	
	Configs(URI uri) {
		this.uri = uri;
	}
	
	public Config getConfig(String configName) {
		return new Config(UriBuilder.fromUri(uri).path("config").path(configName).build());
	}
	
	public Config getDefaultConfig() {
		return getConfig("default-config");
	}
	
	public Config getServerConfig() {
		return getConfig("server-config");
	}
}
