package jp.coppermine.glassfish.runtime;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

public class JavaRuntimeTest {

    @Ignore
    @Test
    public void testGetRuntime() {
        fail("まだ実装されていません");
    }

    @Test
    public void testExecute() throws InterruptedException {
        final Path javaHome = Paths.get("C:", "Program Files", "Java", "jre7");
        final Path glassfishBase = Paths.get("C:", "glassfish4");
        final Path adminCli = Paths.get("glassfish", "modules", "admin-cli.jar");

        JavaRuntime runtime = JavaRuntime.getRuntime(javaHome);
        int start = runtime.execute(glassfishBase.resolve(adminCli), "start-domain").waitFor();
        assertThat(start, is(0));
        int stop = runtime.execute(glassfishBase.resolve(adminCli), "stop-domain").waitFor();
        assertThat(stop, is(0));
    }

    @Test
    public void testGetJvmPath() {
        final Path jreHome = Paths.get("C:", "Program Files", "Java", "jre7");
        final Path jdkHome = Paths.get("C:", "Program Files", "Java", "jdk1.7.0_25", "jre");

        final Path jvmPath = Paths.get("C:", "Program Files", "Java", "jdk1.7.0_25", "bin", "java.exe");

        JavaRuntime jre = JavaRuntime.getRuntime(jreHome);
        assertThat(jre.getJvmPath(), is(jvmPath));

        JavaRuntime jdk = JavaRuntime.getRuntime(jdkHome);
        assertThat(jdk.getJvmPath(), is(jvmPath));
    }

}
