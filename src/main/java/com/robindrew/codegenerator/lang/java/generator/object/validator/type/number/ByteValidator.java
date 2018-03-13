package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class ByteValidator extends NumberValidator<Byte> {

	public ByteValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Byte parseNumber(String value) {
		return Byte.parseByte(value);
	}

}
