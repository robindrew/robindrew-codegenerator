package com.robindrew.codegenerator.lang.java.type;

import java.util.List;

import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public class JavaTypeContainer implements IJavaType {

	private final IJavaType delegate;
	private final boolean isExtends;
	private final boolean isSuper;

	public JavaTypeContainer(IJavaType type, boolean isExtends, boolean isSuper) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.delegate = type;
		this.isExtends = isExtends;
		this.isSuper = isSuper;
	}

	@Override
	public boolean isExtends() {
		return isExtends;
	}

	@Override
	public boolean isSuper() {
		return isSuper;
	}

	@Override
	public String getPackageName() {
		return delegate.getPackageName();
	}

	@Override
	public boolean isSamePackage(IJavaType type) {
		return type.isSamePackage(type);
	}

	@Override
	public boolean requiresImport() {
		return delegate.requiresImport();
	}

	@Override
	public String getSimpleName() {
		return delegate.getSimpleName();
	}

	@Override
	public String getSimpleName(boolean includeGenerics) {
		return delegate.getSimpleName(includeGenerics);
	}

	@Override
	public String getName() {
		return delegate.getName();
	}

	@Override
	public String getDeclaration() {
		return delegate.getDeclaration();
	}

	@Override
	public String getDeclaration(boolean includePackage) {
		return delegate.getDeclaration(includePackage);
	}

	@Override
	public String getDefaultValue() {
		return delegate.getDefaultValue();
	}

	@Override
	public boolean isDefaultPackage() {
		return delegate.isDefaultPackage();
	}

	@Override
	public IJavaType getUnderlying() {
		return delegate.getUnderlying();
	}

	@Override
	public IClassType getClassType() {
		return delegate.getClassType();
	}

	@Override
	public List<IJavaType> getGenericsList() {
		return delegate.getGenericsList();
	}

	@Override
	public List<IJavaType> getUnderlyingTypes() {
		return delegate.getUnderlyingTypes();
	}

	@Override
	public int getGenericsCount() {
		return delegate.getGenericsCount();
	}

	@Override
	public boolean isPrimitive() {
		return delegate.isPrimitive();
	}

	@Override
	public boolean isInterface() {
		return delegate.isInterface();
	}

	@Override
	public boolean isEnum() {
		return delegate.isEnum();
	}

	@Override
	public boolean isAnnotation() {
		return delegate.isAnnotation();
	}

	@Override
	public boolean isVoid() {
		return delegate.isVoid();
	}

	@Override
	public boolean isGenericSymbol() {
		return delegate.isGenericSymbol();
	}

	@Override
	public boolean isArray() {
		return delegate.isArray();
	}

	@Override
	public boolean isBoolean() {
		return delegate.isBoolean();
	}

	@Override
	public IJavaType getComponentType() {
		return delegate.getComponentType();
	}

	@Override
	public int compareTo(IJavaType o) {
		return delegate.compareTo(o);
	}

	@Override
	public boolean isInstanceOf(Class<?> type) {
		return delegate.isInstanceOf(type);
	}

	@Override
	public boolean isType(Class<?>... types) {
		return delegate.isType(types);
	}

	@Override
	public int getDimensions() {
		return delegate.getDimensions();
	}

	@Override
	public IJavaType toObjectType() {
		if (isVoid() || isPrimitive()) {
			return delegate.toObjectType();
		}
		return this;
	}

	@Override
	public Class<?> getType() {
		return delegate.getType();
	}

	@Override
	public IJavaType getEnclosingClass() {
		return delegate.getEnclosingClass();
	}

}
