package com.robindrew.codegenerator.lang.java.generator.model.common;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.model.object.common.ModelMethodParameter;

public class JavaModelMethodParameter extends JavaModelTyped<ModelMethodParameter> {

	public JavaModelMethodParameter(ModelMethodParameter model) {
		super(model);
	}

	public String getName() {
		return get().getName();
	}

	public IJavaNamedType toNamedType() {
		return new JavaNamedType(getName(), getType());
	}
}
