package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveLongValidator extends PrimitiveNumberValidator<Long> {

	public PrimitiveLongValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Long parseNumber(String value) {
		return Long.parseLong(value);
	}

}
