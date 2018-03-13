package com.robindrew.codegenerator.lang.java.generator.object.factory.method;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class NewInstanceMethod extends JavaMethod {

	public NewInstanceMethod(IJavaTypeResolver resolver, IJavaType type) {
		super("newInstance", type);
		setOverride();
		getParameters().add("key", Object.class);
		String name = type.getSimpleName(true);

		// Contents
		JavaCodeLines code = new JavaCodeLines();
		code.line("Class<? extends " + name + "> type = getType(key);");
		code.line("try {");
		code.line(1, "return type.newInstance();");
		code.line("} catch (Exception e) {");
		code.line(1, "throw new IllegalStateException(\"Unable to instantiate \" + type, e);");
		code.line("}");
		setContents(code);
	}
}
