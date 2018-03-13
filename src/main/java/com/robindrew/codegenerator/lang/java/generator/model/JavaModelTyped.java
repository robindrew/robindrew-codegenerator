package com.robindrew.codegenerator.lang.java.generator.model;

import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class JavaModelTyped<M> {

	private final M model;
	private volatile IJavaType type;

	public JavaModelTyped(M model) {
		if (model == null) {
			throw new NullPointerException("model");
		}
		this.model = model;
	}

	public M get() {
		return model;
	}

	public IJavaType getType() {
		if (type == null) {
			throw new IllegalStateException("type not set");
		}
		return type;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	public boolean hasType() {
		return type != null;
	}

}
