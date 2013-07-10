package jp.coppermine.glassfish.management;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

public class Configs {

    private URI uri;

    Configs(URI uri) {
    	this.uri = uri;
    }
    
    Configs(UriBuilder uriBuilder) {
        this.uri = uriBuilder.build();
    }

    public URI getUri() {
        return uri;
    }

    public Config config(String name) {
        return new Config(UriBuilder.fromUri(uri).path("config").path(name));
    }

    public Config defaultConfig() {
        return config("default-config");
    }

    public Config serverConfig() {
        return config("server-config");
    }

    public Set<String> listConfigs() {
    	Client client = ClientBuilder.newClient();
    	WebTarget target = client.target(uri).path("list-configs");
    	String json = target.request(APPLICATION_JSON).get(String.class);
    	
    	JsonReader jsonReader = Json.createReader(new StringReader(json));
    	JsonObject root = jsonReader.readObject();
    	
    	String command = root.getString("command");
    	String exitCode = root.getString("exit_code");
    	String message = root.getString("message");
    	if (!exitCode.equals("SUCCESS")) {
    		throw new RuntimeException(String.format("[command=%s][exit_code=%s]%s", command, exitCode, message));
    	}
    	
        return new HashSet<>(Arrays.asList(message.split("\n", -1)));
    }

    public void copyConfig() {
        // TODO should implement
    }
}
