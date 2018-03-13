package com.robindrew.codegenerator.lang.java.generator.object.validator.type.collection;

import com.robindrew.codegenerator.lang.java.generator.object.validator.JavaValidator;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class CollectionValidator extends JavaValidator {

	public CollectionValidator(ModelValidator validator) {
		super(validator);
	}

	@Override
	public void appendMethodTo(IJavaCodeLines code, String argumentName, IJavaTypeSet typeSet) {
		appendNotNull(code, argumentName);

		if (isNull()) {
			code.line(0, "if (" + argumentName + " != null) {");
			code.setIndent(1);
		}
		appendNotEmpty(code, argumentName);
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

		int maxLength = Integer.parseInt(max);
		code.line(0, "if (" + argumentName + ".size() > " + maxLength + ") {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " too large, maximum of " + maxLength + " elements, value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

	private void appendMin(IJavaCodeLines code, String argumentName) {
		String min = getValidator().getMin();
		if (min == null) {
			return;
		}

		int minLength = Integer.parseInt(min);
		code.line(0, "if (" + argumentName + ".size() < " + minLength + ") {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " too small, minimum of " + minLength + " elements, value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

}
