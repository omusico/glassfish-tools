package jp.coppermine.glassfish.runtime;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class JavaRuntime {
	
	private JavaRuntime() { } 
	
	public Process execute(Path java, Path jar, String...command) {
		List<String> commandList = new ArrayList<>(Arrays.asList(java.toString(), "-jar", jar.toString()));
		commandList.addAll(Arrays.asList(command));
		ProcessBuilder pb = new ProcessBuilder(commandList);
		try {
			return pb.start();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public Process execute(File java, File jar, String...command) {
		return execute(java.toPath(), jar.toPath(), command);
	}
	
	public Process execute(String java, String jar, String...command) {
		return execute(Paths.get(java), Paths.get(jar), command);
	}
	
	protected Properties getSystemProperties() {
		for (String key : System.getProperties().stringPropertyNames()) {
			System.out.printf("%s=%s\n", key, System.getProperties().get(key));
		}
		return System.getProperties();
	}
	
	private static Path javaHome = Paths.get(System.getProperty("java.home"));
	
	Path getJRE() {
		return getJRE(javaHome);
	}
	
	Path getJRE(Path javaHome) {
		javaHome = javaHome.resolve("bin");
		Path java = OperatingSystem.autoDetect().java();
		return Files.exists(javaHome.resolve(java)) ? javaHome.resolve(java).toAbsolutePath() : null;
	}
	
	Path getJDK() {
		return getJDK(javaHome);
	}
	
	Path getJDK(Path javaHome) {
		javaHome = javaHome.resolve("bin");
		Path javac = OperatingSystem.autoDetect().javac();
		return Files.exists(javaHome.resolve(javac)) ? javaHome.resolve(javac).toAbsolutePath() : null;
	}
	
	Path getLatestJDK() {
		return getLatestJDK("");
	}
	
	Path getLatestJDK(final String version) {
		if (getJRE() != null && getJDK() != null) {
			System.out.println(getJRE());
			return getJRE();
		} else {
			if (OperatingSystem.adapt(System.getProperty("os.name")) == OperatingSystem.WINDOWS) {
				File javaBase = Paths.get(System.getProperty("java.home")).getParent().toFile();
				File[] files = javaBase.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						return name.startsWith("jdk" + version);
					}
				});
				Arrays.sort(files, new Comparator<File>() {
					@Override
					public int compare(File o1, File o2) {
						return -o1.compareTo(o2);
					}
				});
				for (File file : files) {
					System.out.println(file.toPath());
				}
				System.out.printf("detect: %s\n", files[0].toPath());
				return getJRE(files[0].toPath());
			}
			return null;
		}
	}
	
	public static void main(String...args) throws InterruptedException {
		JavaRuntime runtime = new JavaRuntime();
		System.out.printf("boot: %s\n", runtime.getJRE());
		System.out.printf("jdk: %s\n", runtime.getLatestJDK());
		Process process = runtime.execute(runtime.getLatestJDK(), Paths.get("C:", "glassfish4", "glassfish", "modules", "admin-cli.jar"), "start-domain");
		int result = process.waitFor();
		System.out.println(result);
	}
	
}
