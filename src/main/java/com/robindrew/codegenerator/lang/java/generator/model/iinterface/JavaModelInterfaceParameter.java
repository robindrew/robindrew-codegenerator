package com.robindrew.codegenerator.lang.java.generator.model.iinterface;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterfaceParameter;

public class JavaModelInterfaceParameter extends JavaModelTyped<ModelInterfaceParameter> {

	public JavaModelInterfaceParameter(ModelInterfaceParameter model) {
		super(model);
	}

	public String getName() {
		return get().getName();
	}

	public IJavaNamedType toNamedType() {
		return new JavaNamedType(getName(), getType());
	}
}
