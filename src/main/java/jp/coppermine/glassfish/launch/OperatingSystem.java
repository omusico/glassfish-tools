package jp.coppermine.glassfish.launch;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum OperatingSystem {
	WINDOWS("Windows"), LINUX("Linux"), MAC_OS("Mac OS X"), SOLARIS("SunOS"), AIX("AIX"), HP_UX("HP-UX"), FREE_BSD("FreeBSD");
	
	private String name;
	
	OperatingSystem(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public static OperatingSystem autoDetect() {
		for (OperatingSystem os : OperatingSystem.values()) {
			if (System.getProperty("os.name").contains(os.toString())) {
				return os;
			}
		}
		return null;
	}
	
	public static OperatingSystem adapt(String osName) {
		for (OperatingSystem value : values()) {
			if (osName.contains(value.name)) {
				return value;
			}
		}
		return null;
	}
	
	public Path java() {
		return this == WINDOWS ? Paths.get("java.exe") : Paths.get("java");
	}
	
	public Path javaw() {
		return this == WINDOWS ? Paths.get("javaw.exe") : Paths.get("java");
	}
	
	public Path javac() {
		return this == WINDOWS ? Paths.get("javac.exe") : Paths.get("javac");
	}
}
