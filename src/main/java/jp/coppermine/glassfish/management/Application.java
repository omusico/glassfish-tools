package jp.coppermine.glassfish.management;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Application {

    private UriBuilder uriBuilder;

    Application(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }
}
