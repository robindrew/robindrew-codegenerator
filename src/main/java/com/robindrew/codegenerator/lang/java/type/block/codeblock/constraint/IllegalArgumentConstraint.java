package com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeAppendable;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;

public class IllegalArgumentConstraint implements IJavaCodeAppendable {

	private final String name;
	private final String operator;
	private final Object value;

	public IllegalArgumentConstraint(String name, String operator, Object value) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		if (operator.isEmpty()) {
			throw new IllegalArgumentException("operator is empty");
		}
		this.name = name;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public IJavaCodeLines appendTo(IJavaCodeLines lines) {
		lines.line(0, "if (" + name + " " + operator + " " + value + ") {");
		lines.line(1, "throw new IllegalArgumentException(\"" + name + "\" " + operator + " " + value + ");");
		lines.line(0, "}");
		return lines;
	}
}
