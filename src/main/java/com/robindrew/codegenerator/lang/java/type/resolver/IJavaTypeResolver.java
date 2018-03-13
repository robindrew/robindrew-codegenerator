package com.robindrew.codegenerator.lang.java.type.resolver;

import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public interface IJavaTypeResolver {

	Set<IJavaType> getRegisteredTypes();

	<T> IJavaType registerClass(Class<T> clazz, int generics);

	<T> IJavaType registerClass(Class<T> clazz);

	<T> IJavaType registerAlias(Class<T> clazz, String alias, int generics);

	<T> IJavaType registerAlias(Class<T> clazz, String alias);

	<T> IJavaType registerAlias(Class<T> clazz);

	<T> IJavaType registerAlias(Class<T> clazz, int generics);

	<T> IJavaType getType(Class<T> clazz);

	IJavaType parseClassType(String text);

	IJavaType registerAlias(IJavaType type, String alias);

	IJavaType registerAlias(IJavaType type);

	IJavaType resolveJavaType(String text);

	IJavaType registerAndAliasJavaType(String packageName, String simpleName, IClassType classType);

}
