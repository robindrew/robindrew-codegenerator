package com.robindrew.codegenerator.lang.java.type.interfacemap;

import static java.util.Collections.emptySet;

import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;

public class JavaExtendsMap implements IJavaExtendsMap {

	private final SetMultimap<IJavaType, IJavaType> typeMap = HashMultimap.create();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void put(Class<?> from, Class<?> to) {
		JavaTypeClass<?> interfaceType = new JavaTypeClass(from);
		JavaTypeClass<?> objectType = new JavaTypeClass(to);
		put(interfaceType, objectType);
	}

	@Override
	public void put(IJavaType type, IJavaType extendsType) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		if (extendsType == null) {
			throw new NullPointerException("extendsType");
		}
		if (type.equals(extendsType)) {
			throw new IllegalArgumentException(type + " cannot extend itself!");
		}
		synchronized (typeMap) {
			typeMap.put(type, extendsType);
		}
	}

	@Override
	public Set<IJavaType> get(IJavaType type) {
		synchronized (typeMap) {
			Set<IJavaType> types = typeMap.get(type);
			if (types == null) {
				return emptySet();
			}
			return types;
		}
	}

	@Override
	public IJavaType getInstance(IJavaType type) {
		for (Entry<IJavaType, IJavaType> entry : typeMap.entries()) {
			IJavaType key = entry.getKey();
			IJavaType value = entry.getValue();
			if (value.equals(type) && !key.isInterface()) {
				return key;
			}
		}
		throw new IllegalArgumentException("Unable find a concrete instance of type: " + type);
	}
}
