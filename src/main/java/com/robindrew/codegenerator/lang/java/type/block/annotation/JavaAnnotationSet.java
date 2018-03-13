package com.robindrew.codegenerator.lang.java.type.block.annotation;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class JavaAnnotationSet implements IJavaAnnotationSet {

	private final Set<IJavaAnnotation> set = new LinkedHashSet<IJavaAnnotation>();

	@Override
	public boolean add(IJavaAnnotation annotation) {
		return set.add(annotation);
	}

	@Override
	public Iterator<IJavaAnnotation> iterator() {
		return set.iterator();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public int size() {
		return set.size();
	}

}
