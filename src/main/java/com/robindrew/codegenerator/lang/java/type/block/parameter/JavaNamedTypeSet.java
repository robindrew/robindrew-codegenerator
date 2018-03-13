package com.robindrew.codegenerator.lang.java.type.block.parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;

public class JavaNamedTypeSet implements IJavaNamedTypeSet {

	private final IJavaTypeSet references;
	private final Set<IJavaNamedType> set = new LinkedHashSet<IJavaNamedType>();

	public JavaNamedTypeSet(IJavaTypeSet references) {
		if (references == null) {
			throw new NullPointerException("references");
		}
		this.references = references;
	}

	public JavaNamedTypeSet(IJavaClassBlock block) {
		this(block.getReferences());
	}

	public JavaNamedTypeSet() {
		this(new JavaTypeSet());
	}

	@Override
	public JavaNamedTypeSet add(IJavaNamedType parameter) {
		if (!set.add(parameter)) {
			throw new IllegalArgumentException("Duplicate parameter: " + parameter);
		}
		references.add(parameter.getType());
		return this;
	}

	@Override
	public JavaNamedTypeSet addAll(Collection<? extends IJavaNamedType> parameters) {
		for (IJavaNamedType parameter : parameters) {
			add(parameter);
		}
		return this;
	}

	@Override
	public IJavaNamedTypeSet addAll(IJavaNamedTypeSet parameters) {
		return addAll(parameters.getAll());
	}

	@Override
	public JavaNamedTypeSet addAll(IJavaNamedType... parameters) {
		return addAll(Arrays.asList(parameters));
	}

	@Override
	public JavaNamedTypeSet add(IJavaName name, IJavaType type) {
		return add(new JavaNamedType(name, type));
	}

	@Override
	public JavaNamedTypeSet add(String name, Class<?> type) {
		return add(new JavaNamedType(name, type));
	}

	@Override
	public JavaNamedTypeSet add(IJavaName name, Class<?> type) {
		return add(new JavaNamedType(name, type));
	}

	@Override
	public JavaNamedTypeSet add(String name, IJavaType type) {
		return add(new JavaNamedType(name, type));
	}

	@Override
	public Iterator<IJavaNamedType> iterator() {
		return set.iterator();
	}

	@Override
	public int hashCode() {
		return set.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof JavaNamedTypeSet) {
			JavaNamedTypeSet set = (JavaNamedTypeSet) object;
			if (!this.set.equals(set.set)) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean contains(IJavaNamedType parameter) {
		return set.contains(parameter);
	}

	@Override
	public List<IJavaNamedType> getAll() {
		return new ArrayList<IJavaNamedType>(set);
	}

	@Override
	public IJavaNamedTypeSet add(String name, IJavaType type, boolean isFinal) {
		return add(new JavaNamedType(name, type, isFinal));
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public int size() {
		return set.size();
	}

}
