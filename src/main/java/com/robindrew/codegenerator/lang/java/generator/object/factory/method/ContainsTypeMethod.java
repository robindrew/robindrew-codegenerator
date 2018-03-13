package com.robindrew.codegenerator.lang.java.generator.object.factory.method;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class ContainsTypeMethod extends JavaMethod {

	public ContainsTypeMethod(IJavaTypeResolver resolver, IJavaType type) {
		super("containsType", boolean.class);
		setOverride();
		getParameters().add("key", Object.class);

		// Contents
		JavaCodeLines code = new JavaCodeLines();
		code.line("return objectMap.containsKey(key);");
		setContents(code);
	}

}
