package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveByteValidator extends PrimitiveNumberValidator<Byte> {

	public PrimitiveByteValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Byte parseNumber(String value) {
		return Byte.parseByte(value);
	}

}
