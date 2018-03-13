package com.robindrew.codegenerator.lang.java.generator.object.factory.method;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class GetTypeMethod extends JavaMethod {

	private static IJavaType getReturnType(IJavaTypeResolver resolver, IJavaType type) {
		String name = type.getSimpleName(true);
		return resolver.resolveJavaType("Class<? extends " + name + ">");
	}

	public GetTypeMethod(IJavaTypeResolver resolver, IJavaType type) {
		super("getType", getReturnType(resolver, type));
		setOverride();
		getParameters().add("key", Object.class);
		String name = type.getSimpleName(true);

		// Contents
		JavaCodeLines code = new JavaCodeLines();
		new NullPointerConstraint("key").appendTo(code);
		code.line("Class<? extends " + name + "> type = objectMap.get(key);");
		code.line("if (type == null) {");
		code.line(1, "throw new IllegalArgumentException(\"key: '\" + key + \"'\");");
		code.line("}");
		code.line("return type;");
		setContents(code);
	}

}
