package com.robindrew.codegenerator.lang.java.type.interfacemap;

import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;

public interface IJavaExtendsMap {

	void put(IJavaType type, IJavaType extendsType);

	void put(Class<?> type, Class<?> extendsType);

	Set<IJavaType> get(IJavaType type);

	IJavaType getInstance(IJavaType type);

}
