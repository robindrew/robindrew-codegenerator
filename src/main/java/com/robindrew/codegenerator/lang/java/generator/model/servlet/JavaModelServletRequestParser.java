package com.robindrew.codegenerator.lang.java.generator.model.servlet;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.servlet.ModelServletRequestParser;

public class JavaModelServletRequestParser {

	private final ModelServletRequestParser parser;
	private volatile IJavaType type;

	public JavaModelServletRequestParser(ModelServletRequestParser serializer) {
		this.parser = serializer;
	}

	public ModelServletRequestParser get() {
		return parser;
	}

	public String getName() {
		return parser.getName();
	}

	public IJavaType getType() {
		return type;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

}
