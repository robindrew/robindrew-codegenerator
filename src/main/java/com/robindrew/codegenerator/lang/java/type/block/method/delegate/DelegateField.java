package com.robindrew.codegenerator.lang.java.type.block.method.delegate;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;

public class DelegateField extends JavaField {

	public static final String DEFAULT_NAME = "delegate";

	public DelegateField(String name, IJavaType type) {
		super(name, type);
		setFinal(true);
	}

	public DelegateField(IJavaType type) {
		this(DEFAULT_NAME, type);
	}
}
