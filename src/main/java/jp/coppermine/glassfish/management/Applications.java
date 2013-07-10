package jp.coppermine.glassfish.management;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

public class Applications {

    public static class Deploy {

    }

    public static class Undeploy {

    }

    private UriBuilder uriBuilder;

    Applications(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public Application application() {
        return new Application(uriBuilder.clone().path("application"));
    }

    public Map<String, String> listApplications() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uriBuilder).path("list-applications");
        String json = target.request().accept(APPLICATION_JSON).get(String.class);

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject root = reader.readObject();
        if (!root.getString("exit_code").equals("SUCCESS")) {
            throw new RuntimeException();
        }

        JsonObject properties = root.getJsonObject("properties");
        if (properties == null) {
            return Collections.emptyMap();
        }

        Map<String, String> apps = new HashMap<>();
        for (String key : properties.keySet()) {
            apps.put(key, properties.getString(key));
        }
        return apps;
    }

    public List<String> listApplicationRefs() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uriBuilder).path("list-application-refs");
        String json = target.request().accept(APPLICATION_JSON).get(String.class);

        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject root = reader.readObject();
        if (!root.getString("exit_code").equals("SUCCESS")) {
            throw new RuntimeException();
        }

        String message = root.getString("message");
        if (message == null) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<>();
        for (String key : message.split("\n")) {
            list.add(key.trim());
        }
        return list;
    }

    public void deploy(Deploy deploy) {

    }

    public void undeploy(Undeploy undeploy) {

    }
}
