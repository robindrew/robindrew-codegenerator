package com.robindrew.codegenerator.lang.java.generator.object.factory.field;

import java.util.concurrent.ConcurrentHashMap;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class FactoryMapField extends JavaField {

	private static IJavaType getType(IJavaTypeResolver resolver, IJavaType type) {
		String name = type.getSimpleName(true);
		return resolver.resolveJavaType("Map<Object, Class<? extends " + name + ">>");
	}

	public FactoryMapField(IJavaTypeResolver resolver, IJavaType type) {
		super("objectMap", getType(resolver, type));
		setFinal(true);

		String name = type.getSimpleName(true);
		setAssignment("new ConcurrentHashMap<Object, Class<? extends " + name + ">>();");
		getReferences().add(ConcurrentHashMap.class);
		getReferences().add(type);
	}

}
