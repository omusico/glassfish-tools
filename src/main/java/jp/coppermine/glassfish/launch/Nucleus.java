package jp.coppermine.glassfish.launch;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Nucleus {
	
	public class Execute {
		private Execute() { }
		
		private int executeProcess(Process process) {
			try {
				return process.waitFor();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return Integer.MIN_VALUE;
			}
		}
		
		public int admin(String...subCommands) {
			return executeProcess(Nucleus.this.admin(subCommands));
		}
		
		public int startServer() {
			return executeProcess(Nucleus.this.startServer());
		}
		
		public int startServer(String domain) {
			return executeProcess(Nucleus.this.startServer(domain));
		}
		
		public int stopServer() {
			return executeProcess(Nucleus.this.stopServer());
		}
		
		public int stopServer(String domain) {
			return executeProcess(Nucleus.this.stopServer(domain));
		}
	}
	
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
	
	public Execute execute() {
		return new Execute();
	}
}
