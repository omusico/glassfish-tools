package jp.coppermine.glassfish.monitoring;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public class Monitor {
    private final UriBuilder uriBuilder;

    public Monitor(URI uri) {
        this.uriBuilder = UriBuilder.fromUri(uri);
    }

    public Monitor(UriBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public URI getUri() {
        return uriBuilder.build();
    }
}
