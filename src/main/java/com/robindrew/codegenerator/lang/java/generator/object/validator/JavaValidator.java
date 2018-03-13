package com.robindrew.codegenerator.lang.java.generator.object.validator;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NotEmptyConstraint;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.constraint.NullPointerConstraint;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public abstract class JavaValidator implements IJavaValidator {

	private final ModelValidator validator;

	protected JavaValidator(ModelValidator validator) {
		if (validator == null) {
			throw new NullPointerException("validator");
		}
		this.validator = validator;
	}

	public ModelValidator getValidator() {
		return validator;
	}

	protected void appendNotNull(IJavaCodeLines lines, String argumentName) {
		if (getValidator().isNotNull()) {
			new NullPointerConstraint(argumentName).appendTo(lines);
		}
	}

	protected void appendNotEmpty(IJavaCodeLines lines, String argumentName) {
		if (getValidator().isNotEmpty()) {
			new NotEmptyConstraint(argumentName).appendTo(lines);
		}
	}

	protected boolean isNull() {
		return !getValidator().isNotNull();
	}

}
