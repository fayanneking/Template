package com.orangeandbronze.template;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


public class TestVariableSegment {
	
	private Map<String, String> variables;
	
	@Before
	public void setUp() {
		variables = new HashMap<String, String>();
	}
	
	@Test
	public void variableEvaluatesToItsValue() throws Exception {
		String name = "myvar";
		String value = "myvalue";
		variables.put(name, value);
		assertEquals(value, new Variable(name).evaluate(variables));
	}
	
	@Test
	public void missingVariableRaisesException() throws Exception {
		String name = "myvar";
		try {
			new Variable(name).evaluate(variables);
			fail("Missing variable value should raise an exception");
		} catch (MissingValueException expected) {
			assertEquals("No value for ${" + name + "}", expected.getMessage());
		}
	}
	
}
