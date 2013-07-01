package jp.coppermine.glassfish.management;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.UriBuilder;

public class Domain {
	public static final Domain DEFAULT = new Domain("localhost", 4848);
	
	public static final String X_REQUESTED_BY = "X-Requested-By";
	public static final String X_REQUESTED_BY_VALUE = "GlassFish REST HTML interface";
	
	private final URI baseUri;
	
	public Domain(String host, int port) {
		baseUri = UriBuilder.fromPath("management").path("domain").scheme("http").host(host).port(port).build();
	}
	
	public URI getBaseUri() {
		return baseUri;
	}
	
	public Configs getConfigs() {
		URI uri = UriBuilder.fromUri(baseUri).path("configs").build();
		return new Configs(uri);
	}
	
	public Applications getApplication() {
		URI uri = UriBuilder.fromUri(baseUri).path("applications").build();
		return new Applications(uri);
	}
	
	public void restartDomain() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri(baseUri).path("restart-domain"));
		target.request().header(X_REQUESTED_BY, X_REQUESTED_BY_VALUE).post(Entity.form(new Form()));
	}
	
	public void stopDomain() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri(baseUri).path("stop"));
		target.request().header(X_REQUESTED_BY, X_REQUESTED_BY_VALUE).post(Entity.form(new Form()));
	}
}
