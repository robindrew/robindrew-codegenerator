package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class IntegerValidator extends NumberValidator<Integer> {

	public IntegerValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Integer parseNumber(String value) {
		return Integer.parseInt(value);
	}

}
