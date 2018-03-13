package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveFloatValidator extends PrimitiveNumberValidator<Float> {

	public PrimitiveFloatValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Float parseNumber(String value) {
		return Float.parseFloat(value);
	}

}
