package jp.coppermine.glassfish.management;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

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

    private UriBuilder uriBuilder;

    JavaConfig(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public Set<String> getJvmOptions() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uriBuilder).path("jvm-options");
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
        WebTarget target = client.target(uriBuilder).path("jvm-options");
        Response response = target.request().header("X-Requested-By", "GlassFish REST HTML interface")
                .accept(APPLICATION_JSON).put(Entity.entity(option, APPLICATION_FORM_URLENCODED));
        System.out.println("[add] HTTP " + response.getStatus());
    }

    public void removeJvmOption(String option) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uriBuilder).path("jvm-options");
        Response response = target.queryParam(option, "").request()
                .header("X-Requested-By", "GlassFish REST HTML interface").accept(APPLICATION_JSON).delete();
        System.out.println("[remove] HTTP " + response.getStatus());
    }
}
