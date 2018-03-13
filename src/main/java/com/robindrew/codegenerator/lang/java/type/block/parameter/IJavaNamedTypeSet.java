package com.robindrew.codegenerator.lang.java.type.block.parameter;

import java.util.Collection;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public interface IJavaNamedTypeSet extends Iterable<IJavaNamedType> {

	boolean isEmpty();

	boolean contains(IJavaNamedType parameter);

	IJavaNamedTypeSet add(IJavaName name, IJavaType type);

	IJavaNamedTypeSet add(String name, Class<?> type);

	IJavaNamedTypeSet add(IJavaName name, Class<?> type);

	IJavaNamedTypeSet add(String name, IJavaType type);

	IJavaNamedTypeSet add(String name, IJavaType type, boolean isFinal);

	IJavaNamedTypeSet add(IJavaNamedType parameter);

	IJavaNamedTypeSet addAll(IJavaNamedType... parameter);

	IJavaNamedTypeSet addAll(Collection<? extends IJavaNamedType> parameter);

	IJavaNamedTypeSet addAll(IJavaNamedTypeSet parameters);

	List<IJavaNamedType> getAll();

	void clear();

	int size();

}
