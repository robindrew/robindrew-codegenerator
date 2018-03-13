package com.robindrew.codegenerator.lang.java.type;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.ANNOTATION;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.ENUM;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.GENERIC_SYMBOL;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.PRIMITIVE;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.VOID;
import static java.util.Collections.emptyList;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;

public abstract class AbstractJavaType implements IJavaType {

	public static final String EXTENDS = "? extends ";
	public static final String SUPER = "? super ";

	public static final String DEFAULT_NULL = "null";
	public static final int UNKNOWN_GENERICS_COUNT = -1;

	private final String packageName;
	private final String simpleName;
	private final int hash;

	protected AbstractJavaType(String packageName, String simpleName) {
		if (packageName == null) {
			throw new NullPointerException("packageName");
		}
		if (simpleName.isEmpty()) {
			throw new IllegalArgumentException("simpleName is empty");
		}
		this.packageName = packageName;
		this.simpleName = simpleName;
		this.hash = (packageName + simpleName).hashCode();
	}

	@Override
	public IJavaType getUnderlying() {
		return this;
	}

	@Override
	public String getPackageName() {
		return packageName;
	}

	@Override
	public boolean isSamePackage(IJavaType type) {
		return getPackageName().equals(type.getPackageName());
	}

	@Override
	public boolean requiresImport() {
		return !isPrimitive() && !getPackageName().equals("java.lang");
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public List<IJavaType> getUnderlyingTypes() {
		return Arrays.asList(getUnderlying());
	}

	@Override
	public String getSimpleName(boolean includeGenerics) {
		StringBuilder text = new StringBuilder();
		text.append(getSimpleName());
		if (includeGenerics) {
			appendGenerics(text);
		}
		return text.toString();
	}

	@Override
	public String getDeclaration() {
		return getDeclaration(false);
	}

	@Override
	public String getDeclaration(boolean includePackage) {
		StringBuilder text = new StringBuilder();
		if (includePackage && !isDefaultPackage()) {
			text.append(getPackageName()).append('.');
		}
		text.append(getSimpleName());
		appendGenerics(text);
		return text.toString();
	}

	private void appendGenerics(StringBuilder text) {
		if (getGenericsList().isEmpty()) {
			return;
		}
		text.append('<');
		boolean comma = false;
		for (IJavaType generics : getGenericsList()) {
			if (comma) {
				text.append(", ");
			}
			comma = true;
			if (generics.isExtends()) {
				text.append(EXTENDS);
			}
			if (generics.isSuper()) {
				text.append(SUPER);
			}
			text.append(generics.getSimpleName(true));
		}
		text.append('>');
	}

	@Override
	public boolean isDefaultPackage() {
		return packageName.isEmpty();
	}

	@Override
	public List<IJavaType> getGenericsList() {
		return emptyList();
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof IJavaType) {
			IJavaType that = (IJavaType) object;
			if (!getPackageName().equals(that.getPackageName())) {
				return false;
			}
			if (!getSimpleName().equals(that.getSimpleName())) {
				return false;
			}
			if (getGenericsCount() != -1 && that.getGenericsCount() != -1) {
				if (getGenericsCount() != that.getGenericsCount()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(IJavaType type) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(getPackageName(), type.getPackageName());
		builder.append(getSimpleName(), type.getSimpleName());
		return builder.toComparison();
	}

	@Override
	public String toString() {
		return getSimpleName(true);
	}

	@Override
	public String getName() {
		if (isDefaultPackage()) {
			return getSimpleName();
		}
		return getPackageName() + "." + getSimpleName();
	}

	@Override
	public boolean isPrimitive() {
		return PRIMITIVE.equals(getClassType());
	}

	@Override
	public boolean isInterface() {
		return INTERFACE.equals(getClassType());
	}

	@Override
	public boolean isArray() {
		return false;
	}

	@Override
	public boolean isEnum() {
		return ENUM.equals(getClassType());
	}

	@Override
	public boolean isAnnotation() {
		return ANNOTATION.equals(getClassType());
	}

	@Override
	public boolean isVoid() {
		return VOID.equals(getClassType());
	}

	public boolean isGenericSymbol() {
		return GENERIC_SYMBOL.equals(getClassType());
	}

	@Override
	public int getGenericsCount() {
		return UNKNOWN_GENERICS_COUNT;
	}

	@Override
	public String getDefaultValue() {
		// Null for everything except primitives
		return DEFAULT_NULL;
	}

	@Override
	public boolean isExtends() {
		return false;
	}

	@Override
	public boolean isSuper() {
		return false;
	}

	@Override
	public boolean isBoolean() {
		return getSimpleName().equalsIgnoreCase("boolean");
	}

	@Override
	public IJavaType getComponentType() {
		return null;
	}

	@Override
	public boolean isInstanceOf(Class<?> type) {
		return false;
	}

	@Override
	public boolean isType(Class<?>... types) {
		return false;
	}

	@Override
	public int getDimensions() {
		return 0;
	}

	@Override
	public Class<?> getType() {
		throw new IllegalStateException("not a class type");
	}

	@Override
	public IJavaType toObjectType() {

		// Void?
		if (isVoid()) {
			if (isType(void.class)) {
				return new JavaTypeClass<Void>(Void.class);
			}
			return this;
		}

		// Primitive
		if (isPrimitive()) {
			if (isType(byte.class)) {
				return new JavaTypeClass<Byte>(Byte.class);
			}
			if (isType(short.class)) {
				return new JavaTypeClass<Short>(Short.class);
			}
			if (isType(int.class)) {
				return new JavaTypeClass<Integer>(Integer.class);
			}
			if (isType(long.class)) {
				return new JavaTypeClass<Long>(Long.class);
			}
			if (isType(float.class)) {
				return new JavaTypeClass<Float>(Float.class);
			}
			if (isType(double.class)) {
				return new JavaTypeClass<Double>(Double.class);
			}
			if (isType(boolean.class)) {
				return new JavaTypeClass<Boolean>(Boolean.class);
			}
			if (isType(char.class)) {
				return new JavaTypeClass<Character>(Character.class);
			}
		}
		return this;
	}

	@Override
	public IJavaType getEnclosingClass() {
		return null;
	}

}
