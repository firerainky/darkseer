package com.zky.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


class FragmentsApplicationTests {

	@Test
	void test_addNullToAList_shouldReallyAddingAndNoExceptionThrowing() {
		List<String> list = new ArrayList<>();
		list.add(null);
		assertEquals(1, list.size());
	}
}
