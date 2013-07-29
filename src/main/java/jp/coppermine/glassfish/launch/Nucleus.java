package jp.coppermine.glassfish.launch;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Nucleus {
	private String javaHome;
	private String installRoot;
	
	public Nucleus(String javaHome, String installRoot) {
		if (!Files.exists(Paths.get(javaHome))) {
			throw new RuntimeException(new FileNotFoundException(String.format("${java.home}: %s is not found", javaHome)));
		}
		if (!Files.exists(Paths.get(installRoot))) {
			throw new RuntimeException(new FileNotFoundException(String.format("${install.root}: %s is not found", installRoot)));
		}
		
		this.javaHome = javaHome;
		this.installRoot = installRoot;
	}
	
	public Process admin(String...subCommands) {
		Path adminCliPath = Paths.get(installRoot, "modules", "admin-cli.jar");
		if (!Files.exists(adminCliPath)) {
			throw new RuntimeException(new FileNotFoundException(String.format("%s is not found", adminCliPath.toString())));
		}
		return JavaRuntime.of(javaHome).execute(adminCliPath.toString(), subCommands);
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
