package com.robindrew.codegenerator.lang.java.generator.object.validator.type;

import com.robindrew.codegenerator.lang.java.generator.object.validator.JavaValidator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class ObjectValidator extends JavaValidator {

	private final IJavaType type;

	public ObjectValidator(IJavaType type, ModelValidator validator) {
		super(validator);
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	@Override
	public void appendMethodTo(IJavaCodeLines lines, String argumentName, IJavaTypeSet typeSet) {

		// Only the null check is available to unknown Object types (primitives are all supported)
		appendNotNull(lines, argumentName);
	}

	public IJavaType getType() {
		return type;
	}
}
