package com.robindrew.codegenerator.lang.java.generator.model.eenum;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.model.object.eenum.ModelEnumField;

public class JavaModelEnumField extends JavaModelTyped<ModelEnumField> {

	public JavaModelEnumField(ModelEnumField model) {
		super(model);
	}

	public String getName() {
		return get().getName();
	}

	public IJavaNamedType toNamedType() {
		return new JavaNamedType(getName(), getType());
	}
}
