package jp.coppermine.glassfish.launch;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class JavaWindowsRuntime extends JavaRuntime {
	
	protected JavaWindowsRuntime() {
		super();
	}
	
	protected JavaWindowsRuntime(JavaVersion version) {
		super(version);
	}
	
	protected JavaWindowsRuntime(String javaHome) {
		super(javaHome);
	}
	
	@Override
	protected String findJavaHome(JavaVersion version) {
		String[] nativeJDKs = findJDKs(Paths.get(getProgramFiles(), "Java").toString(), version);
		if (nativeJDKs.length > 0) {
			return Paths.get(getProgramFiles(), "Java", nativeJDKs[0]).toString();
		}
		
		String[] wow64JDKs = findJDKs(Paths.get(getWoW64ProgramFiles(), "Java").toString(), version);
		if (wow64JDKs.length > 0) {
			return Paths.get(getWoW64ProgramFiles(), "Java", wow64JDKs[0]).toString();
		}
		
		throw new RuntimeException(new FileNotFoundException("JDK is not found"));
	}
	
	protected String getProgramFiles() {
		if (OperatingSystem.autoDetect() != OperatingSystem.WINDOWS) {
			throw new IllegalStateException();
		}
		
		return System.getenv("ProgramFiles");
	}
	
	protected String getWoW64ProgramFiles() {
		if (OperatingSystem.autoDetect() != OperatingSystem.WINDOWS) {
			throw new IllegalStateException();
		}
		
		String pathWoW64 = System.getenv("ProgramW6432");
		if (getProgramFiles().equals(pathWoW64)) {
			return pathWoW64 + " (x86)";
		} else {
			return pathWoW64;
		}
	}
}
