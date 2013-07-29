package jp.coppermine.glassfish.launch;

public enum Architecture {
	X86("x86"), X64("amd64"), ITANIUM("ia64"), SPARC("sparcv9");
	
	private String name;
	
	Architecture(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public static Architecture autoDetect() {
		for (Architecture arch : values()) {
			if (System.getProperty("os.arch").equalsIgnoreCase(arch.toString())) {
				return arch;
			}
		}
		return null;
	}
	
	public static Architecture adapt(String osArch) {
		for (Architecture arch : values()) {
			if (osArch.equalsIgnoreCase(arch.toString())) {
				return arch;
			}
		}
		return null;
	}
}
