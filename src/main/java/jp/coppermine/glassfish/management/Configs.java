package jp.coppermine.glassfish.management;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.core.UriBuilder;

public class Configs {

    private UriBuilder uriBuilder;

    Configs(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public Config config(String name) {
        return new Config(uriBuilder.clone().path("config").path(name));
    }

    public Config defaultConfig() {
        return config("default-config");
    }

    public Config serverConfig() {
        return config("server-config");
    }

    public Set<String> listConfigs() {
        // TODO should implement
        return null;
    }

    public void copyConfig() {
        // TODO should implement
    }
}
