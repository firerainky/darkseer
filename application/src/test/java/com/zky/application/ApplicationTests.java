package com.zky.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		String version = System.getProperty("java.version");
		System.out.println("Java version: " + version);
	}
}
