package jp.coppermine.glassfish.util;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.nio.file.Paths;

import org.junit.Test;

public class RuntimeTest {

	@Test
	public void test() throws InterruptedException {
		Runtime.Builder builder = Runtime.jdk(Paths.get("C:", "Program Files", "Java", "jdk1.7.0_25")).installDir(Paths.get("C:", "glassfish4"));
		int code1 = builder.start().waitFor();
		assertThat(code1, is(0));
		int code2 = builder.restart().waitFor();
		assertThat(code2, is(0));
		int code3 = builder.stop().waitFor();
		assertThat(code3, is(0));
	}

}
