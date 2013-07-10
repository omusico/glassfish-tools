package jp.coppermine.glassfish.management;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Set;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

public class ConfigsTest {

	private UriBuilder uriBuilder = UriBuilder.fromUri("http://localhost:4848/management/domain/configs");

	@Test
	public void testConfigs() {
		fail("まだ実装されていません");
	}

	@Test
	public void testGetUri() {
		fail("まだ実装されていません");
	}

	@Test
	public void testConfig() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDefaultConfig() {
		Configs configs = new Configs(uriBuilder);
		assertThat(configs.defaultConfig(), is(configs.config("default-config")));
	}

	@Test
	public void testServerConfig() {
		Configs configs = new Configs(uriBuilder);
		assertThat(configs.serverConfig(), is(configs.config("sever-config")));
	}

	@Test
	public void testListConfigs() {
		Configs configs = new Configs(uriBuilder);
		Set<String> set = configs.listConfigs();
		assertThat(set, hasItems("default-config", "server-config"));
	}

	@Test
	public void testCopyConfig() {
		fail("まだ実装されていません");
	}

}
