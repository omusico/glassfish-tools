package jp.coppermine.glassfish;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.ws.rs.core.UriBuilder;

import jp.coppermine.glassfish.management.Manager;
import jp.coppermine.glassfish.monitoring.Monitor;

public class GlassFish {

    private UriBuilder uriBuilder;

    private GlassFish(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public static GlassFish server(UriBuilder uriBuilder) {
        return new GlassFish(uriBuilder);
    }

    public static GlassFish server(URI uri) {
        return new GlassFish(UriBuilder.fromUri(uri));
    }

    public static GlassFish server(String host, int port) {
        UriBuilder uriBuilder = UriBuilder.fromPath("").host(host).port(port).scheme("http");
        return new GlassFish(uriBuilder);
    }

    public static GlassFish server(String url) {
        UriBuilder uriBuilder = UriBuilder.fromUri(url);
        return new GlassFish(uriBuilder);
    }

    public GlassFish secure(boolean flag) {
        uriBuilder.scheme(flag ? "https" : "http");
        return this;
    }

    public URI getUri() {
        return uriBuilder.build();
    }

    public Manager manager() {
        return new Manager(uriBuilder.clone().path("management").path("domain"));
    }

    public Monitor monitor() {
        return new Monitor(uriBuilder.clone().path("monitoring").path("domain"));
    }

    public static class Directories {
        public static Path home() {
            return Paths.get("glassfish");
        }

        public static Path modules() {
            return home().resolve("modules");
        }

        public static Path adminCli() {
            return modules().resolve("admin-cli.jar");
        }
    }
}
