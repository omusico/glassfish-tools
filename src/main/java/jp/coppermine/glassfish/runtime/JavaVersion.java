package jp.coppermine.glassfish.runtime;

public enum JavaVersion {
	JDK5("1.5.0"), JDK6("1.6.0"), JDK7("1.7.0"), JDK8("1.8.0"), JDK81("1.8.1");
	
	String version;
	
	JavaVersion(String version) {
		this.version = version;
	}
	
	JavaVersion(JavaVersion version) {
		this.version = version.toString();
	}

	@Override
	public String toString() {
		return version;
	}
}
