package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveIntValidator extends PrimitiveNumberValidator<Integer> {

	public PrimitiveIntValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Integer parseNumber(String value) {
		return Integer.parseInt(value);
	}

}
