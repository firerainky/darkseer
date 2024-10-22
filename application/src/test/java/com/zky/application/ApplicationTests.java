package com.zky.application;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@Test
	void contextLoads() {
		String version = System.getProperty("java.version");
		System.out.println("Java version: " + version);
	}

	@Test
	void test_asyncPrint_shouldNotPrintSinceTestIsOver() throws Exception {
		ResultActions actions = mockMvc
                .perform(MockMvcRequestBuilders.get("/asyncPrint"));

		actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

		// If we want the code async task being executed, comment below line to wait aysnc task being finished.
		// Thread.sleep(2000);
	}
}
