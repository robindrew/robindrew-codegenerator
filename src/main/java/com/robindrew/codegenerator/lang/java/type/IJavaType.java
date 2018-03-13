package com.robindrew.codegenerator.lang.java.type;

import java.util.List;

import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public interface IJavaType extends Comparable<IJavaType> {

	String getPackageName();

	boolean isSamePackage(IJavaType type);

	boolean requiresImport();

	String getSimpleName();

	String getSimpleName(boolean includeGenerics);

	String getName();

	String getDeclaration();

	String getDeclaration(boolean includePackage);

	String getDefaultValue();

	boolean isDefaultPackage();

	IJavaType getUnderlying();

	IClassType getClassType();

	List<IJavaType> getGenericsList();

	List<IJavaType> getUnderlyingTypes();

	int getGenericsCount();

	boolean isPrimitive();

	boolean isInterface();

	boolean isEnum();

	boolean isAnnotation();

	boolean isVoid();

	boolean isGenericSymbol();

	boolean isArray();

	boolean isExtends();

	boolean isSuper();

	boolean isBoolean();

	IJavaType getComponentType();

	boolean isInstanceOf(Class<?> type);

	boolean isType(Class<?>... type);

	int getDimensions();

	IJavaType toObjectType();

	Class<?> getType();

	IJavaType getEnclosingClass();

}
