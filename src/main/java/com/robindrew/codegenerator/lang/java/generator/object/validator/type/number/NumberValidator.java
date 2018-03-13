package com.robindrew.codegenerator.lang.java.generator.object.validator.type.number;

import com.robindrew.codegenerator.lang.java.generator.object.validator.JavaValidator;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public abstract class NumberValidator<N extends Number> extends JavaValidator {

	protected NumberValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	public void appendMethodTo(IJavaCodeLines code, String argumentName, IJavaTypeSet typeSet) {
		appendNotNull(code, argumentName);

		if (isNull()) {
			code.line(0, "if (" + argumentName + " != null) {");
			code.setIndent(1);
		}
		appendMin(code, argumentName);
		appendMax(code, argumentName);
		if (isNull()) {
			code.setIndent(-1);
			code.line(0, "}");
		}
	}

	private void appendMax(IJavaCodeLines code, String argumentName) {
		String max = getValidator().getMax();
		if (max == null) {
			return;
		}

		Number maxValue = parseNumber(max);
		code.line(0, "if (" + argumentName + " > " + maxValue + ") {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " too large, maximum of " + maxValue + ", value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

	private void appendMin(IJavaCodeLines code, String argumentName) {
		String min = getValidator().getMin();
		if (min == null) {
			return;
		}

		Number minValue = parseNumber(min);
		code.line(0, "if (" + argumentName + " < " + minValue + ") {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " too small, minimum of " + minValue + ", value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

	protected abstract N parseNumber(String value);

}
