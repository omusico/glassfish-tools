package jp.coppermine.glassfish.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runtime {
	public static class Builder {
		private Path jdk;
		private Path dir;
		private Path admincli = Paths.get("glassfish", "modules", "admin-cli.jar");
		private String domainName = "domain1";
		
		private Builder(Path javaHome) {
			if (System.getProperty("file.separator").equals("\\")) {
				jdk = javaHome.resolve("bin").resolve("java.exe");
			} else {
				jdk = javaHome.resolve("bin").resolve("java");
			}
		}
		
		public Builder installDir(Path dir) {
			this.dir = dir;
			return this;
		}
		
		public Builder domain(String domainName) {
			this.domainName = domainName;
			return this;
		}
		
		private Process run(String subCommand) {
			try {
				return new ProcessBuilder(jdk.toString(), "-jar", dir.resolve(admincli).toString(), subCommand, domainName).start();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		public Process start() {
			return run("start-domain");
		}
		
		public Process restart() {
			return run("restart-domain");
		}
		
		public Process stop() {
			return run("stop-domain");
		}
	}
	
	public static Builder jdk(Path javaHome) {
		return new Builder(javaHome);
	}
	
	public static Builder jdk(String javaHome) {
		return new Builder(Paths.get(javaHome));
	}
}
