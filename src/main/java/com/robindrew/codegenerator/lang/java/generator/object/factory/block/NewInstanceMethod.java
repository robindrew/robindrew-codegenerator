package com.robindrew.codegenerator.lang.java.generator.object.factory.block;

import java.util.concurrent.ConcurrentHashMap;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class NewInstanceMethod extends JavaMethod {

	public NewInstanceMethod(IJavaType returnType) {
		super("newInstance", returnType);
		getParameters().add("name", String.class);

		// Contents
		JavaCodeLines lines = new JavaCodeLines();
		lines.line("Class<?> clazz = map.get(name);");
		lines.line("if (clazz == null) {");
		lines.line(1, "throw new IllegalArgumentException(\"name: '\" + name + \"'\");");
		lines.line("}");
		lines.line("try {");
		lines.line(1, "return (" + returnType.getSimpleName() + ") clazz.newInstance();");
		lines.line("} catch (Exception e) {");
		lines.line(1, "throw new IllegalStateException(\"Unable to instantiate \" + clazz, e);");
		lines.line("}");
		setContents(lines);

		getReferences().add(ConcurrentHashMap.class);
	}
}
