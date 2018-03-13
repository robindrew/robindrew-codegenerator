package com.robindrew.codegenerator.lang.java.generator.object.field;

import static com.robindrew.codegenerator.lang.java.type.block.visibility.JavaVisibility.PUBLIC;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.field.JavaField;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class ConstantField extends JavaField {

	public ConstantField(IJavaName name, IJavaType type) {
		super(name, type);
		getReferences().add(type);

		setVisibility(PUBLIC);
		setStatic(true);
		setFinal(true);
		setConstant(true);
	}

	public ConstantField(String name, IJavaType type) {
		this(new JavaName(name), type);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConstantField(String name, Class<?> type) {
		this(new JavaName(name), new JavaTypeClass(type));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConstantField(IJavaName name, Class<?> type) {
		this(name, new JavaTypeClass(type));
	}

	public ConstantField(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType());
	}

}
