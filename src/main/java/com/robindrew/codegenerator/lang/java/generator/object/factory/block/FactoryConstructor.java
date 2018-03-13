package com.robindrew.codegenerator.lang.java.generator.object.factory.block;

import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.constructor.JavaConstructor;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class FactoryConstructor extends JavaConstructor {

	public FactoryConstructor(IJavaName name, Set<IJavaType> types) {
		super(name);

		JavaCodeLines lines = new JavaCodeLines();
		for (IJavaType type : types) {
			lines.line("register(\"" + type.getSimpleName() + "\", " + type.getSimpleName() + ".class);");
		}
		setContents(lines);
	}

}
