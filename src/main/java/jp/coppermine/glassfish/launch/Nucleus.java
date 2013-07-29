package jp.coppermine.glassfish.launch;

import java.nio.file.Paths;

public class Nucleus {
	private String javaHome;
	private String installRoot;
	
	public Nucleus(String javaHome, String installRoot) {
		this.javaHome = javaHome;
		this.installRoot = installRoot;
	}
	
	public Process admin(String...subCommands) {
		String adminCliPath = Paths.get(installRoot, "modules", "admin-cli.jar").toString();
		return JavaRuntime.getRuntime(javaHome).execute(adminCliPath, subCommands);
	}
	
	public Process startServer() {
		return admin("start-domain");
	}
	
	public Process startServer(String domain) {
		return admin("start-domain", domain);
	}
	
	public Process stopServer() {
		return admin("stop-domain");
	}
	
	public Process stopServer(String domain) {
		return admin("stop-domain", domain);
	}
}
