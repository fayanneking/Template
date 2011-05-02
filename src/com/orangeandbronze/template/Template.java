package com.orangeandbronze.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {
	
	private Map<String, String> variables;
	private String templateText;
	
	public Template(String templateText) {
		variables = new HashMap<String, String>();
		this.templateText = templateText;
	}
	
	public void set(String variable, String value) {
		variables.put(variable, value);
	}
	
	public String evaluate() {
		TemplateParse parser = new TemplateParse();
		List<Segment> segments = parser.parseSegments(templateText);
		return concatenate(segments);
	}
	
	private String concatenate(List<Segment> segments){
		StringBuilder result = new StringBuilder();
		for (Segment segment : segments) {
			result.append(segment.evaluate(variables));
		}
		return result.toString();
	}

	public static boolean isVariable(String segment) {
		return segment.startsWith("${") && segment.endsWith("}");
	}

}
