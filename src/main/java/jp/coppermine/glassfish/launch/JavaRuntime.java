package jp.coppermine.glassfish.launch;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class JavaRuntime {
	private String javaHome;
	
	protected JavaRuntime() {
		this.javaHome = findJavaHome();
	}
	
	protected JavaRuntime(JavaVersion version) {
		this.javaHome = findJavaHome(version);
	}
	
	protected JavaRuntime(String javaHome) {
		this.javaHome = javaHome;
	}
	
	protected abstract String findJavaHome();
	
	protected abstract String findJavaHome(JavaVersion version);
	
	public String getJavaHome() {
		return javaHome;
	}
	
	public static JavaRuntime find() {
		return of(null);
	}
	
	public static JavaRuntime of(String javaHome) {
		OperatingSystem os = OperatingSystem.autoDetect();
		switch (os) {
		case WINDOWS:
			return javaHome == null ? new JavaWindowsRuntime() : new JavaWindowsRuntime(javaHome);
		case MAC_OS:
			throw new UnsupportedOperationException(String.format("Unsupported System: %s", os));
		case LINUX:
			throw new UnsupportedOperationException(String.format("Unsupported System: %s", os));
		case SOLARIS:
			throw new UnsupportedOperationException(String.format("Unsupported System: %s", os));
		case AIX:
			throw new UnsupportedOperationException(String.format("Unsupported System: %s", os));
		case FREE_BSD:
			throw new UnsupportedOperationException(String.format("Unsupported System: %s", os));
		default:
			throw new IllegalStateException(String.format("Unknown System Architecture: %s", os.toString()));
		}
	}
	
	protected String[] findJDKs(String parent, final JavaVersion version) {
		String[] files = new File(parent).list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("jdk" + version);
			}
		});
		Arrays.sort(files, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return -o1.compareTo(o2);
			}
		});
		return files;
	}
	
	public Process execute(String jarPath, String...commands) {
		List<String> commandList = new ArrayList<>();
		commandList.add(Paths.get(getJavaHome(), "bin", OperatingSystem.autoDetect().java()).toString());
		commandList.add("-jar");
		commandList.add(jarPath);
		commandList.addAll(Arrays.asList(commands));
        ProcessBuilder pb = new ProcessBuilder(commandList);
        try {
            return pb.start();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
	}
}
