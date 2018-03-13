package com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeAppendable;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;

public class NullPointerConstraint implements IJavaCodeAppendable {

	private final String name;

	public NullPointerConstraint(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		this.name = name;
	}

	public NullPointerConstraint(IJavaNamedType type) {
		this(type.getName().get());
		if (type.getType().isPrimitive()) {
			throw new IllegalArgumentException("primitive type: " + type);
		}
	}

	@Override
	public IJavaCodeLines appendTo(IJavaCodeLines lines) {
		lines.line(0, "if (" + name + " == null) {");
		lines.line(1, "throw new NullPointerException(\"" + name + "\");");
		lines.line(0, "}");
		return lines;
	}
}
