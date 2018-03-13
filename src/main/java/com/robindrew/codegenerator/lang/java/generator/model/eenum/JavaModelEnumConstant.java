package com.robindrew.codegenerator.lang.java.generator.model.eenum;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.eenum.ModelEnumConstant;

public class JavaModelEnumConstant extends JavaModelTyped<ModelEnumConstant> {

	private final List<JavaModelEnumField> fieldList;

	public JavaModelEnumConstant(ModelEnumConstant constant, List<JavaModelEnumField> fieldList) {
		super(constant);
		this.fieldList = fieldList;
	}

	public String getName() {
		return get().getName();
	}

	public IJavaType getReturnType() {
		return getType();
	}

	public List<JavaModelEnumField> getFieldList() {
		return fieldList;
	}

}
