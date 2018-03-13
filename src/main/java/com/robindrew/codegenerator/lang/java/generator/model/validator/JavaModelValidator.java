package com.robindrew.codegenerator.lang.java.generator.model.validator;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.generator.object.validator.IJavaValidator;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class JavaModelValidator extends JavaModelTyped<ModelValidator> {

	private volatile IJavaValidator javaValidator;

	public JavaModelValidator(ModelValidator validator) {
		super(validator);
	}

	public String getName() {
		return get().getName();
	}

	public IJavaValidator getJavaValidator() {
		return javaValidator;
	}

	public void setJavaValidator(IJavaValidator validator) {
		if (validator == null) {
			throw new NullPointerException("validator");
		}
		this.javaValidator = validator;
	}

}
