package jp.coppermine.glassfish.runtime;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum OperatingSystem {
	WINDOWS("Windows"), LINUX("Linux"), MAC_OS("Mac OS X"), SOLARIS("SunOS"), AIX("AIX"), HP_UX("HP-UX"), FREE_BSD("FreeBSD");
	
	private String keyword;
	
	OperatingSystem(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return keyword;
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
			if (osName.contains(value.keyword)) {
				return value;
			}
		}
		return null;
	}
	
	public Path java() {
		return command("java");
	}
	
	public Path javac() {
		return command("javac");
	}
	
	protected Path command(String command) {
		return System.getProperty("os.name").contains(keyword) ? Paths.get(command + ".exe") : Paths.get(command);
	}
}
