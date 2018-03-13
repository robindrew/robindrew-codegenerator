package com.robindrew.codegenerator.lang.java.generator.model.bean;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.model.object.bean.ModelBeanParameter;

public class JavaModelBeanParameter extends JavaModelTyped<ModelBeanParameter> {

	public JavaModelBeanParameter(ModelBeanParameter model) {
		super(model);
	}

	public String getName() {
		return get().getName();
	}

	public IJavaNamedType toNamedType() {
		return new JavaNamedType(getName(), getType());
	}
}
