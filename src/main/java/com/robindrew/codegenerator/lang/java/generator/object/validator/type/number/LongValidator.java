package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class LongValidator extends NumberValidator<Long> {

	public LongValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Long parseNumber(String value) {
		return Long.parseLong(value);
	}

}
