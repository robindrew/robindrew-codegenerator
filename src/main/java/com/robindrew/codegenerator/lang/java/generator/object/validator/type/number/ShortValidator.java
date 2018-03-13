package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class ShortValidator extends NumberValidator<Short> {

	public ShortValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Short parseNumber(String value) {
		return Short.parseShort(value);
	}

}
