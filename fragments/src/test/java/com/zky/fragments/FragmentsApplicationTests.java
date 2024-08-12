package com.zky.fragments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


public class FragmentsApplicationTests {

	@Test
	void test_addNullToAList_shouldReallyAddingAndNoExceptionThrowing() {
		List<String> list = new ArrayList<>();
		list.add(null);
		assertEquals(1, list.size());
	}

	@Test
	void test_throwsAnException() {
		AnotherJavaClass object = new AnotherJavaClass();

		Executable executable = () -> object.aMethodThrowsAnException();

		assertThrows(RuntimeException.class, executable);
	}
}

class AnotherJavaClass {
	public void aMethodThrowsAnException() {
		throw new RuntimeException("This is a test exception");
	}
}	
