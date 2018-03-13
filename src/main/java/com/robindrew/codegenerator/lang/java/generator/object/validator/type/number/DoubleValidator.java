package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class DoubleValidator extends NumberValidator<Double> {

	public DoubleValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Double parseNumber(String value) {
		return Double.parseDouble(value);
	}

}
