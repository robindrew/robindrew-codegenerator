package com.robindrew.codegenerator.lang.java.type.set;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

public class JavaTypeSet implements IJavaTypeSet {

	private final boolean sorted;
	private final Set<IJavaType> set;

	public JavaTypeSet(boolean sorted) {
		this.sorted = sorted;
		this.set = newSet(sorted);
	}

	public JavaTypeSet() {
		this(true);
	}

	protected Set<IJavaType> newSet(boolean sorted) {
		if (sorted) {
			return new TreeSet<IJavaType>();
		} else {
			return new LinkedHashSet<IJavaType>();
		}
	}

	@Override
	public Set<IJavaType> toSet() {
		Set<IJavaType> set = newSet(sorted);
		set.addAll(this.set);
		return set;
	}

	@Override
	public Iterator<IJavaType> iterator() {
		return set.iterator();
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean add(IJavaType type) {
		return set.add(type);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean add(Class<?> type) {
		return add(new JavaTypeClass(type));
	}

	@Override
	public IJavaTypeSet subSet(IClassType classType) {
		IJavaTypeSet set = new JavaTypeSet(sorted);
		for (IJavaType type : this) {
			if (type.getClassType().equals(classType)) {
				set.add(type);
			}
		}
		return set;
	}

	@Override
	public void addAll(Iterable<? extends IJavaType> types) {
		for (IJavaType type : types) {
			add(type);
		}
	}

	@Override
	public void addAll(Iterable<? extends IJavaType> types, boolean generics) {
		for (IJavaType type : types) {
			add(type);

			// Recursive
			if (generics) {
				addAll(type.getGenericsList(), true);
			}
		}
	}

	@Override
	public String toString() {
		return set.toString();
	}

}
