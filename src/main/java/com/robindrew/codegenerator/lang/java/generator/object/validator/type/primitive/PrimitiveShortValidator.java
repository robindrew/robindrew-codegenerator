package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveShortValidator extends PrimitiveNumberValidator<Short> {

	public PrimitiveShortValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Short parseNumber(String value) {
		return Short.parseShort(value);
	}

}
