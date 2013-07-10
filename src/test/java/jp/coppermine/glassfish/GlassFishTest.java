package jp.coppermine.glassfish;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

public class GlassFishTest {

    @Test
    public void testServer_UriBuilder() throws URISyntaxException {
        UriBuilder uriBuilder = UriBuilder.fromPath("").host("localhost").port(4848).scheme("http");
        GlassFish glassFish = GlassFish.server(uriBuilder);
        assertThat(glassFish.getUri(), is(new URI("http://localhost:4848")));
        assertThat(glassFish.getUri().toString(), is("http://localhost:4848"));
    }

    @Test
    public void testServer_URI() throws URISyntaxException {
        URI uri = UriBuilder.fromPath("").host("localhost").port(4848).scheme("http").build();
        GlassFish glassFish = GlassFish.server(uri);
        assertThat(glassFish.getUri(), is(new URI("http://localhost:4848")));
        assertThat(glassFish.getUri().toString(), is("http://localhost:4848"));
    }

    @Test
    public void testServer_host_port() throws URISyntaxException {
        GlassFish glassFish = GlassFish.server("localhost", 4848);
        assertThat(glassFish.getUri(), is(new URI("http://localhost:4848")));
        assertThat(glassFish.getUri().toString(), is("http://localhost:4848"));
    }

    @Test
    public void testServer_URL() throws URISyntaxException {
        GlassFish glassFish = GlassFish.server("http://localhost:4848");
        assertThat(glassFish.getUri(), is(new URI("http://localhost:4848")));
        assertThat(glassFish.getUri().toString(), is("http://localhost:4848"));
    }

    @Test
    public void testServer_URL2() throws URISyntaxException {
        GlassFish glassFish = GlassFish.server("http://localhost:4848/");
        assertThat(glassFish.getUri(), is(new URI("http://localhost:4848/")));
        assertThat(glassFish.getUri().toString(), is("http://localhost:4848/"));
    }

    @Test
    public void testSecure_http() throws URISyntaxException {
        GlassFish glassFish = GlassFish.server("http://localhost:4848").secure(false);
        assertThat(glassFish.getUri(), is(new URI("http://localhost:4848")));
        assertThat(glassFish.getUri().toString(), is("http://localhost:4848"));
    }

    @Test
    public void testSecure_https() throws URISyntaxException {
        GlassFish glassFish = GlassFish.server("http://localhost:4848").secure(true);
        assertThat(glassFish.getUri(), is(new URI("https://localhost:4848")));
        assertThat(glassFish.getUri().toString(), is("https://localhost:4848"));
    }
}
