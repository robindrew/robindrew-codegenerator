package com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeAppendable;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;

/**
 * Not empty constraint, useful for strings, collections and maps.
 */
public class NotEmptyConstraint implements IJavaCodeAppendable {

	private final String name;

	public NotEmptyConstraint(String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		this.name = name;
	}

	public NotEmptyConstraint(IJavaNamedType type) {
		this(type.getName().get());
	}

	@Override
	public IJavaCodeLines appendTo(IJavaCodeLines lines) {
		lines.line(0, "if (" + name + ".isEmpty()) {");
		lines.line(1, "throw new IllegalArgumentException(\"" + name + " is empty\");");
		lines.line(0, "}");
		return lines;
	}
}
