package jp.coppermine.glassfish.launch;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JavaRuntimeTest {
	
	@BeforeClass
	public static void setUpClass() throws IOException {
		Path parent = Paths.get(System.getProperty("java.io.tmpdir"), "Java");
		Files.createDirectory(parent);
		Files.createDirectory(parent.resolve("jdk1.8.0"));
		Files.createDirectory(parent.resolve("jdk1.7.0_25"));
		Files.createDirectory(parent.resolve("jdk1.7.0_21"));
		Files.createDirectory(parent.resolve("jdk1.7.0_03"));
		Files.createDirectory(parent.resolve("jdk1.7.0_01"));
		Files.createDirectory(parent.resolve("jdk1.6.0_43"));
		Files.createDirectory(parent.resolve("jdk1.6.0_24"));
		Files.createDirectory(parent.resolve("jdk1.6.0_22"));
		Files.createDirectory(parent.resolve("jdk1.6.0_17"));
		Files.createDirectory(parent.resolve("jdk1.6.0_13"));
		Files.createDirectory(parent.resolve("jdk1.6.0_07"));
		Files.createDirectory(parent.resolve("jdk1.6.0"));
		Files.createDirectory(parent.resolve("jdk1.5.0_22"));
	}
	
	@AfterClass
	public static void tearDownClass() throws IOException {
		Path parent = Paths.get(System.getProperty("java.io.tmpdir"), "Java");
		Files.delete(parent.resolve("jdk1.8.0"));
		Files.delete(parent.resolve("jdk1.7.0_25"));
		Files.delete(parent.resolve("jdk1.7.0_21"));
		Files.delete(parent.resolve("jdk1.7.0_03"));
		Files.delete(parent.resolve("jdk1.7.0_01"));
		Files.delete(parent.resolve("jdk1.6.0_43"));
		Files.delete(parent.resolve("jdk1.6.0_24"));
		Files.delete(parent.resolve("jdk1.6.0_22"));
		Files.delete(parent.resolve("jdk1.6.0_17"));
		Files.delete(parent.resolve("jdk1.6.0_13"));
		Files.delete(parent.resolve("jdk1.6.0_07"));
		Files.delete(parent.resolve("jdk1.6.0"));
		Files.delete(parent.resolve("jdk1.5.0_22"));
		Files.delete(parent);
	}
	
	@Test
	public void testJavaRuntime() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testJavaRuntime_String() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testGetJavaHome() {
		JavaRuntime runtime = JavaRuntime.find();
		assertThat(runtime.getJavaHome(), is(Paths.get(System.getenv("ProgramFiles"), "Java", "jdk1.7.0_25").toString()));
	}

	@Test
	public void testGetJavaHome_String() {
		JavaRuntime runtime = JavaRuntime.of(Paths.get(System.getProperty("java.io.tmpdir"), "Java", "jdk1.7.0_21").toString());
		assertThat(runtime.getJavaHome(), is(Paths.get(System.getProperty("java.io.tmpdir"), "Java", "jdk1.7.0_21").toString()));
	}

	@Test
	public void testFind() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testOf() {
//		fail("まだ実装されていません");
	}

	@Test
	public void testFindJDKs() throws IOException {
		String parent = Paths.get(System.getProperty("java.io.tmpdir"), "Java").toString();
		JavaRuntime runtime = new JavaWindowsRuntime();
		String[] files = runtime.findJDKs(parent, JavaVersion.ANY);
		assertThat(files.length, is(13));
		assertThat(files[0], is("jdk1.8.0"));
	}

	@Test
	public void testFindJDKs_JDK7() throws IOException {
		String parent = Paths.get(System.getProperty("java.io.tmpdir"), "Java").toString();
		JavaRuntime runtime = new JavaWindowsRuntime();
		String[] files = runtime.findJDKs(parent, JavaVersion.JDK7);
		assertThat(files.length, is(4));
		assertThat(files[0], is("jdk1.7.0_25"));
	}

	@Test
	public void testExecute() {
//		fail("まだ実装されていません");
	}

}
