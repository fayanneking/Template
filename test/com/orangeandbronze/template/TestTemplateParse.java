package com.orangeandbronze.template;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class TestTemplateParse {
	
	TemplateParse parse = new TemplateParse();
	
	@Test
	public void emptyTemplateRendersAsEmptyString() throws Exception {
		List<String> segments = parse.parse("");
		assertSegments(segments, "");
	}
	
	@Test
	public void templateWithOnlyPlainText() throws Exception {
		List<String> segments = parse.parse("plain text only");
		assertSegments(segments, "plain text only");
	}
	
	@Test
	public void parsingMultipleVariables() throws Exception {
		List<String> segments = parse.parse("${a}:${b}:${c}");
		assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
	}
	
	@Test
	public void parsingTemplateIntoSegmentObjects() throws Exception {
		TemplateParse p = new TemplateParse();
		List<Segment> segments = p.parseSegments("a ${b} c ${d}");
		assertSegments(segments,
				new PlainText("a "), new Variable("b"),
				new PlainText(" c "), new Variable("d"));
								
	}
	
	private void assertSegments(List<? extends Object> actual, Object... expected) {
		assertEquals("Number of segments", expected.length, actual.size());
		assertEquals(Arrays.asList(expected), actual);
	}
}
