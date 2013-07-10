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

    public static final String JDK6 = "1.6";

    public static final String JDK7 = "1.7";

    public static final String JDK8 = "1.8";

    private final Path jvmPath;

    private JavaRuntime(Path jvmPath) {
        this.jvmPath = jvmPath;
    }

    public static JavaRuntime getRuntime(Path javaHome, final String version) {
        javaHome = javaHome.endsWith("jre") ? javaHome.getParent() : javaHome;
        if (getJavaVM(javaHome) != null && getJavaCompiler(javaHome) != null) {
            // Java Runtime Environment within Java Development Kit
            return new JavaRuntime(getJavaVM(javaHome));
        } else {
            // Java Runtime Environment (JRE) only
            if (OperatingSystem.adapt(System.getProperty("os.name")) == OperatingSystem.WINDOWS) {
                // Windows
                File javaBase = javaHome.getParent().toFile();
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
                return new JavaRuntime(getJavaVM(files[0].toPath()));
            } else {
                // TODO Linux, Unix and Mac OS
                throw new UnsupportedOperationException();
            }
        }
    }

    public static JavaRuntime getRuntime(File javaHome, final String version) {
        return getRuntime(javaHome.toPath(), version);
    }

    public static JavaRuntime getRuntime(Path javaHome) {
        return getRuntime(javaHome, "");
    }

    public static JavaRuntime getRuntime(File javaHome) {
        return getRuntime(javaHome, "");
    }

    public static JavaRuntime getRuntime(final String version) {
        return getRuntime(Paths.get(System.getProperty("java.home")), version);
    }

    public static JavaRuntime getRuntime() {
        return getRuntime(Paths.get(System.getProperty("java.home")), "");
    }

    private static Path getJavaVM(Path javaHome) {
        Path java = OperatingSystem.autoDetect().java();
        Path path = javaHome.resolve("bin").resolve(java);
        return Files.exists(path) ? path : null;
    }

    private static Path getJavaCompiler(Path javaHome) {
        Path javac = OperatingSystem.autoDetect().javac();
        Path path = javaHome.resolve("bin").resolve(javac);
        return Files.exists(path) ? path : null;
    }

    public Process execute(Path jar, String... command) {
        List<String> commandList = new ArrayList<>(Arrays.asList(jvmPath.toString(), "-jar", jar.toString()));
        commandList.addAll(Arrays.asList(command));
        ProcessBuilder pb = new ProcessBuilder(commandList);
        try {
            return pb.start();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Process execute(File jar, String... command) {
        return execute(jar.toPath(), command);
    }

    public Process execute(String jar, String... command) {
        return execute(Paths.get(jar), command);
    }

    public Path getJvmPath() {
        return jvmPath;
    }

    protected Properties getSystemProperties() {
        for (String key : System.getProperties().stringPropertyNames()) {
            System.out.printf("%s=%s\n", key, System.getProperties().get(key));
        }
        return System.getProperties();
    }

    public static void main(String... args) throws InterruptedException {
        final Path glassfishBase = Paths.get("C:", "glassfish4");
        final Path adminCli = Paths.get("glassfish", "modules", "admin-cli.jar");

        JavaRuntime runtime = JavaRuntime.getRuntime();
        runtime.getSystemProperties();
        System.out.printf("jre: %s\n", runtime.getJvmPath());
        Process process = runtime.execute(glassfishBase.resolve(adminCli), "start-domain");
        int result = process.waitFor();
        System.out.println(result);
    }
}
