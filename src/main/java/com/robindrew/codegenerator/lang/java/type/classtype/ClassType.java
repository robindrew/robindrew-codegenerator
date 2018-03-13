package com.robindrew.codegenerator.lang.java.type.classtype;

import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public enum ClassType implements IClassType {

	/** The public type. */
	CLASS("class"),
	/** The interface type. */
	INTERFACE("interface"),
	/** The enum type. */
	ENUM("enum"),
	/** The primitive type. */
	PRIMITIVE("primitive"),
	/** The void type. */
	VOID("void"),
	/** The generic type. */
	GENERIC_SYMBOL("generic"),
	/** The annotation type. */
	ANNOTATION("@interface");

	public static IClassType getType(Class<?> clazz) {
		if (clazz.isInterface()) {
			return INTERFACE;
		}
		if (clazz.isEnum()) {
			return ENUM;
		}
		if (clazz.isAnnotation()) {
			return ANNOTATION;
		}
		if (clazz.isPrimitive()) {
			return PRIMITIVE;
		}
		if (clazz == void.class) {
			return VOID;
		}
		if (clazz == Void.class) {
			return VOID;
		}
		return CLASS;
	}

	private final String type;

	private ClassType(String name) {
		this.type = name;
	}

	@Override
	public String get() {
		return type;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.write(type);
		writer.space();
	}

}
