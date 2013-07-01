package jp.coppermine.glassfish.management;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Config {
	private final URI uri;
	
	Config(URI uri) {
		this.uri = uri;
	}
	
	public JavaConfig getJavaConfig() {
		return new JavaConfig(UriBuilder.fromUri(uri).path("java-config").build());
	}
}
