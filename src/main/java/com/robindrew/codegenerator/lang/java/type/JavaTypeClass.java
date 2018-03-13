package com.robindrew.codegenerator.lang.java.type;

import com.robindrew.codegenerator.lang.java.type.classtype.ClassType;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public class JavaTypeClass<T> extends AbstractJavaType {

	public static final IJavaType VOID = new JavaTypeClass<Void>(void.class, 0);

	private static String getPackageName(Class<?> type) {
		Package name = type.getPackage();
		if (name == null) {
			return "";
		}
		return name.getName();
	}

	private final Class<T> type;
	private final int genericsCount;

	public JavaTypeClass(Class<T> type, int genericsCount) {
		super(getPackageName(type), type.getSimpleName());
		if (genericsCount < UNKNOWN_GENERICS_COUNT) {
			throw new IllegalArgumentException("genericsCount=" + genericsCount);
		}
		this.type = type;
		this.genericsCount = genericsCount;
	}

	public JavaTypeClass(Class<T> type) {
		this(type, UNKNOWN_GENERICS_COUNT);
	}

	@Override
	public Class<T> getType() {
		return type;
	}

	@Override
	public IClassType getClassType() {
		return ClassType.getType(getType());
	}

	@Override
	public boolean isGenericSymbol() {
		return false;
	}

	@Override
	public boolean isPrimitive() {
		return getType().isPrimitive();
	}

	@Override
	public boolean isInterface() {
		return getType().isInterface();
	}

	@Override
	public boolean isEnum() {
		return getType().isEnum();
	}

	@Override
	public boolean isArray() {
		return getType().isArray();
	}

	@Override
	public boolean isAnnotation() {
		return getType().isAnnotation();
	}

	@Override
	public boolean isVoid() {
		return getType() == Void.class || getType() == void.class;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public IJavaType getComponentType() {
		Class<?> component = getType().getComponentType();
		if (component != null) {
			return new JavaTypeClass(component);
		}
		return null;
	}

	@Override
	public int getGenericsCount() {
		return genericsCount;
	}

	@Override
	public String getDefaultValue() {
		if (!isPrimitive()) {
			return DEFAULT_NULL;
		}

		// Primitives
		if (getType() == byte.class) {
			return "0";
		}
		if (getType() == short.class) {
			return "0";
		}
		if (getType() == int.class) {
			return "0";
		}
		if (getType() == long.class) {
			return "0l";
		}
		if (getType() == float.class) {
			return "0.0f";
		}
		if (getType() == double.class) {
			return "0.0";
		}
		if (getType() == boolean.class) {
			return "false";
		}
		if (getType() == char.class) {
			return "'\u0000'";
		}
		return DEFAULT_NULL;
	}

	@Override
	public boolean isInstanceOf(Class<?> type) {
		return type.isAssignableFrom(getType());
	}

	@Override
	public boolean isType(Class<?>... types) {
		for (Class<?> type : types) {
			if (getType().equals(type)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getDimensions() {
		int dimensions = 0;
		Class<?> component = getType();
		while (component.isArray()) {
			component = component.getComponentType();
			dimensions++;
		}
		return dimensions;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IJavaType getEnclosingClass() {
		Class<?> enclosing = type.getEnclosingClass();
		if (enclosing == null) {
			return null;
		}
		return new JavaTypeClass(enclosing, UNKNOWN_GENERICS_COUNT);
	}
}
