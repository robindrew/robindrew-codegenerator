package com.robindrew.codegenerator.lang.java.generator.object.factory.block;

import java.util.concurrent.ConcurrentHashMap;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class FactoryMapField extends JavaField {

	private static IJavaType getType(IJavaTypeResolver resolver) {
		return resolver.resolveJavaType("Map<String,Class<?>>");
	}

	public FactoryMapField(IJavaTypeResolver resolver) {
		super("map", getType(resolver));

		setAssignment("new ConcurrentHashMap<String, Class<?>>()");
		getReferences().add(ConcurrentHashMap.class);
	}

}
