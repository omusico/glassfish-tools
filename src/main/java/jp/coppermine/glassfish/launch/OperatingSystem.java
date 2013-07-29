package jp.coppermine.glassfish.launch;


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
	
	public String java() {
		return this == WINDOWS ? "java.exe" : "java";
	}
	
	public String javaw() {
		return this == WINDOWS ? "javaw.exe" : "java";
	}
	
	public String javac() {
		return this == WINDOWS ? "javac.exe" : "javac";
	}
}
