package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.lang.java.generator.object.validator.JavaValidator;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveBooleanValidator extends JavaValidator {

	public PrimitiveBooleanValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	public void appendMethodTo(IJavaCodeLines lines, String argumentName, IJavaTypeSet typeSet) {
		// Nothing to do, no validation available for boolean primitives!
	}

}
