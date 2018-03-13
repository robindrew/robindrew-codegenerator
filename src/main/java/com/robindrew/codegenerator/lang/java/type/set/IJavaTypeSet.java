package com.robindrew.codegenerator.lang.java.type.set;

import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public interface IJavaTypeSet extends Iterable<IJavaType> {

	int size();

	boolean isEmpty();

	boolean add(IJavaType type);

	boolean add(Class<?> type);

	Set<IJavaType> toSet();

	IJavaTypeSet subSet(IClassType type);

	void addAll(Iterable<? extends IJavaType> types);

	void addAll(Iterable<? extends IJavaType> types, boolean generics);

}
