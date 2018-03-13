package com.robindrew.codegenerator.lang.java.generator.object.validator.type.primitive;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class PrimitiveDoubleValidator extends PrimitiveNumberValidator<Double> {

	public PrimitiveDoubleValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Double parseNumber(String value) {
		return Double.parseDouble(value);
	}

}
