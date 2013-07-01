package jp.coppermine.glassfish.management;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static jp.coppermine.glassfish.management.Domain.X_REQUESTED_BY;
import static jp.coppermine.glassfish.management.Domain.X_REQUESTED_BY_VALUE;

import java.io.StringReader;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class JavaConfig {
	private final URI uri;
	
	JavaConfig(URI uri) {
		this.uri = uri;
	}
	
	private static final String JVM_OPTOINS = "jvm-options";
	
	public Set<String> getJvmOptions() {
		Client client = ClientBuilder.newClient();
		System.out.println(UriBuilder.fromUri(uri).path(JVM_OPTOINS).build());
		WebTarget target = client.target(UriBuilder.fromUri(uri).path(JVM_OPTOINS));
		String json = target.request().accept(APPLICATION_JSON).get(String.class);
		
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject root = reader.readObject();
		String exitCode = root.getString("exit_code");
		if (!exitCode.equals("SUCCESS")) {
			throw new RuntimeException();
		}
		JsonObject extraProperties = root.getJsonObject("extraProperties");
		JsonArray leafList = extraProperties.getJsonArray("leafList");
		Set<String> options = new HashSet<>();
		for (JsonValue value : leafList) {
			options.add(value.toString());
		}
		return options;
	}
	
	public void addJvmOption(String option) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri(uri).path(JVM_OPTOINS));
		Response response = target.request().header(X_REQUESTED_BY, X_REQUESTED_BY_VALUE).accept(APPLICATION_JSON).put(Entity.entity(option, APPLICATION_FORM_URLENCODED));
		System.out.println("[add] HTTP " + response.getStatus());
	}
	
	public void removeJvmOption(String option) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(UriBuilder.fromUri(uri).path(JVM_OPTOINS));
		Response response = target.queryParam(option, "").request().header(X_REQUESTED_BY, X_REQUESTED_BY_VALUE).accept(APPLICATION_JSON).delete();
		System.out.println("[remove] HTTP " + response.getStatus());
	}
}
