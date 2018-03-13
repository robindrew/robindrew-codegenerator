package com.robindrew.codegenerator.lang.java.type.block.annotation;

public interface IJavaAnnotationSet extends Iterable<IJavaAnnotation> {

	boolean isEmpty();

	int size();

	boolean add(IJavaAnnotation annotation);

}
