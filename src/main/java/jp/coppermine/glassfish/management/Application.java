package jp.coppermine.glassfish.management;

import java.io.StringReader;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Application {
	private final URI uri;
	
	Application(URI uri) {
		this.uri = uri;
	}
	
	public Map<String, String> listApplications() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri).path("list-applications");
		String json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject root = reader.readObject();
		if (!root.getString("exit_code").equals("SUCCESS")) {
			throw new RuntimeException();
		}
		
		JsonObject properties = root.getJsonObject("properties");
		
		if (properties == null) return Collections.emptyMap();
		
		Map<String, String> apps = new HashMap<>();
		for (String key : properties.keySet()) {
			apps.put(key, properties.getString(key));
			System.out.printf("%s (%s)\n", key, apps.get(key));
		}
		return apps;
	}
	
	public Set<String> listApplicationRefs() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri).path("list-application-refs");
		String json = target.request().accept(MediaType.APPLICATION_JSON).get(String.class);
		
		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject root = reader.readObject();
		if (!root.getString("exit_code").equals("SUCCESS")) {
			throw new RuntimeException();
		}
		
		String message = root.getString("message");
		
		if (message == null) return Collections.emptySet();
		
		Set<String> set = new HashSet<>();
		for (String key : message.split("\n")) {
			set.add(key.trim());
		}
		return set;
	}
	
	public static void main(String...args) {
		Domain domain = new Domain("localhost", 4848);
		System.out.println(domain.getApplication().listApplicationRefs());
	}
}
