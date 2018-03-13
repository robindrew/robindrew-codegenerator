package com.robindrew.codegenerator.lang.java.generator.object.factory.block;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class RegisterMethod extends JavaMethod {

	public RegisterMethod(IJavaType classType) {
		super("register");
		getParameters().add("name", String.class);
		getParameters().add("type", classType);

		// Contents
		JavaCodeLines lines = new JavaCodeLines();
		lines.line("map.put(name, type);");
		setContents(lines);
	}
}
