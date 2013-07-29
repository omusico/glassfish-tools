package jp.coppermine.glassfish.launch;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaRuntime {
	private String javaHome;
	
	private JavaRuntime(String javaHome) {
		this.javaHome = javaHome;
	}
	
	protected String getJavaHome() {
		return javaHome;
	}
	
	public static JavaRuntime getRuntime(String javaHome) {
		return new JavaRuntime(javaHome);
	}
	
	public Process execute(String jarName, String...commands) {
		List<String> commandList = new ArrayList<>();
		commandList.add(Paths.get(getJavaHome(), "bin", OperatingSystem.autoDetect().java()).toString());
		commandList.add("-jar");
		commandList.add(jarName);
		commandList.addAll(Arrays.asList(commands));
        ProcessBuilder pb = new ProcessBuilder(commandList);
        try {
            return pb.start();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
	}
}
