package com.robindrew.codegenerator.lang.java.type.block.annotation;

import java.lang.annotation.Annotation;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaAnnotation implements IJavaAnnotation {

	public static final IJavaAnnotation OVERRIDE = new JavaAnnotation(Override.class);

	private final IJavaType type;
	private final IJavaTypeSet references = new JavaTypeSet();

	public JavaAnnotation(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaAnnotation(Class<? extends Annotation> type) {
		this(new JavaTypeClass(type));
	}

	@Override
	public IJavaType getType() {
		return type;
	}

	@Override
	public IJavaTypeSet getReferences() {
		return references;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.indent();
		writer.write('@');
		writer.write(getType().getSimpleName());
		writer.line();
	}
}
