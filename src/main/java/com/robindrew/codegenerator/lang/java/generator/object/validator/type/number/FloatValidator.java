package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class FloatValidator extends NumberValidator<Float> {

	public FloatValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	protected Float parseNumber(String value) {
		return Float.parseFloat(value);
	}

}
