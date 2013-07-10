package jp.coppermine.glassfish.management;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.net.URI;
import java.util.EnumMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

public class ManagerTest {

	private URI uri = UriBuilder.fromUri("http://localhost:4848/management/domain").build();
	
	@Test
	public void testManagerURI() {
		fail("まだ実装されていません");
	}

	@Test
	public void testManagerUriBuilder() {
		fail("まだ実装されていません");
	}

	@Test
	public void testGetUri() {
		fail("まだ実装されていません");
	}

	@Test
	public void testApplications() {
		fail("まだ実装されていません");
	}

	@Test
	public void testClusters() {
		fail("まだ実装されていません");
	}

	@Test
	public void testConfigs() {
		fail("まだ実装されていません");
	}

	@Test
	public void testJmxUrls() {
		fail("まだ実装されていません");
	}

	@Test
	public void testLbConfigs() {
		fail("まだ実装されていません");
	}

	@Test
	public void testLoadBalancers() {
		fail("まだ実装されていません");
	}

	@Test
	public void testManagedJobConfig() {
		fail("まだ実装されていません");
	}

	@Test
	public void testNodeAgents() {
		fail("まだ実装されていません");
	}

	@Test
	public void testNodes() {
		fail("まだ実装されていません");
	}

	@Test
	public void testProperty() {
		fail("まだ実装されていません");
	}

	@Test
	public void testResources() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSecureAdmin() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSecurityConfigurations() {
		fail("まだ実装されていません");
	}

	@Test
	public void testServers() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSet() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSystemApplications() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSystemProperties() {
		fail("まだ実装されていません");
	}

	@Test
	public void testViewLog() {
		fail("まだ実装されていません");
	}

	@Test
	public void testChangeAdminPassword() {
		fail("まだ実装されていません");
	}

	@Test
	public void testStop() {
		fail("まだ実装されていません");
	}

	@Test
	public void testUnfreezeTransactionService() {
		fail("まだ実装されていません");
	}

	@Test
	public void testConfigureLdapForAdmin() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDeletePasswordAlias() {
		fail("まだ実装されていません");
	}

	@Test
	public void testCreatePasswordAlias() {
		fail("まだ実装されていません");
	}

	@Test
	public void testUpdatePasswordAlias() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListPasswordAliases() {
		fail("まだ実装されていません");
	}

	@Test
	public void testEnableSecureAdmin() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDisableSecureAdmin() {
		fail("まだ実装されていません");
	}

	@Test
	public void testOsgi() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListTimers() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListJvmOptions() {
		Manager manager = new Manager(uri);
		Map<String, Map<String, String>> opts = manager.listJvmOptions("server-config");
		for (String message : opts.keySet()) {
			System.out.print(message);
			for (String key : opts.get(message).keySet()) {
				System.out.printf("[%s=%s]", key, opts.get(message).get(key));
			}
			System.out.println();
		}
		assertTrue(true);
	}

	@Test
	public void testGet() {
		fail("まだ実装されていません");
	}

	@Test
	public void testVersion() {
		Manager manager = new Manager(uri);
		EnumMap<Manager.Version, String> result = manager.version();
		assertThat(result.get(Manager.Version.VERSION), is("GlassFish Server Open Source Edition  4.0 "));
		assertThat(result.get(Manager.Version.VERSION_NUMBER), is("4.0"));
		assertThat(result.get(Manager.Version.FULL_VERSION), is("GlassFish Server Open Source Edition  4.0  (build 89)"));
	}

	@Test
	public void testRestartDomain() {
		fail("まだ実装されていません");
	}

	@Test
	public void testUptime() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListContainers() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListSystemProperties() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListModules() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListCommands() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListBatchJobs() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSetBatchRuntimeConfiguration() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListBatchJobExecutions() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListBatchRuntimeConfiguration() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListBatchJobSteps() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListTransports() {
		fail("まだ実装されていません");
	}

	@Test
	public void testGenerateDomainSchema() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListPersistenceTypes() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListInstances() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListNodes() {
		fail("まだ実装されていません");
	}

	@Test
	public void testCreateInstance() {
		fail("まだ実装されていません");
	}

	@Test
	public void testRestartInstance() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListNodesConfig() {
		fail("まだ実装されていません");
	}

	@Test
	public void testExportSyncBundle() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListLoggers() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDeleteLogLevels() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSetLogFileFormat() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListLogAttributes() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSetLogAttributes() {
		fail("まだ実装されていません");
	}

	@Test
	public void testRotateLog() {
		fail("まだ実装されていません");
	}

	@Test
	public void testSetLogLevels() {
		fail("まだ実装されていません");
	}

	@Test
	public void testCollectLogFiles() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListLogLevels() {
		fail("まだ実装されていません");
	}

	@Test
	public void testDisableMonitoring() {
		fail("まだ実装されていません");
	}

	@Test
	public void testEnableMonitoring() {
		fail("まだ実装されていません");
	}

	@Test
	public void testAddLibrary() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListComponents() {
		fail("まだ実装されていません");
	}

	@Test
	public void testRemoveLibrary() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListLibraries() {
		fail("まだ実装されていません");
	}

	@Test
	public void testListLifecycleModules() {
		fail("まだ実装されていません");
	}

}
