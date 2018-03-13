package com.robindrew.codegenerator.lang.java.generator.object.factory.method;

import java.util.HashSet;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class GetTypesMethod extends JavaMethod {

	public GetTypesMethod(IJavaTypeResolver resolver, IJavaType type) {
		super("getTypes", resolver.resolveJavaType("Set<Class<?>>"));
		setOverride();

		getReferences().add(HashSet.class);

		// Contents
		JavaCodeLines code = new JavaCodeLines();
		code.line("return new HashSet<Class<?>>(objectMap.values());");
		setContents(code);
	}

}
