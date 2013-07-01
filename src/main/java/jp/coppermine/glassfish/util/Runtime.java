package jp.coppermine.glassfish.util;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Arrays;

import javax.ws.rs.core.UriBuilder;

public class Runtime {
	
	public static enum SubCommand {
		START("start-domain"), STOP("stop-domain"), RESTART("restart-doman");
		
		private String subCommand;
		SubCommand(String subCommand) {
			this.subCommand = subCommand;
		}
		
		@Override
		public String toString() {
			return subCommand;
		}
		
	}
	
	private URI uri = UriBuilder.fromPath("glassfish").path("modules").path("admin-cli.jar").build();
	
	private String command;
	
	private Runtime(Path jdk) {
		command = jdk.toString();
	}
	
	private Runtime(String jdk) {
		command = jdk;
	}
	
	public static Runtime of(Path jdk) {
		return new Runtime(jdk);
	}
	
	public static Runtime of(String jdk) {
		return new Runtime(jdk);
	}
	
	public Process domain(SubCommand subCommand) throws IOException {
//		ProcessBuilder pb = new ProcessBuilder().command(command, "-jar", uri.toString(), subCommand.toString());
		System.out.println(Arrays.asList(command, "-jar", "C:", "glassfish4", "glassfish", "modules", "admin-cli.jar", subCommand.toString()));
		ProcessBuilder pb = new ProcessBuilder().command(command, "-jar", "C:", "glassfish4", "glassfish", "modules", "admin-cli.jar", subCommand.toString());
		return pb.start();
	}
}
