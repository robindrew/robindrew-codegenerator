package com.robindrew.codegenerator.lang.java.generator.object.validator.type;

import java.util.regex.Pattern;

import com.robindrew.codegenerator.lang.java.generator.object.validator.JavaValidator;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public class StringValidator extends JavaValidator {

	public StringValidator(ModelValidator validator) {
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
		appendPattern(code, argumentName, typeSet);
		if (isNull()) {
			code.setIndent(-1);
			code.line(0, "}");
		}
	}

	private void appendPattern(IJavaCodeLines code, String argumentName, IJavaTypeSet typeSet) {
		String pattern = getValidator().getPattern();
		if (pattern == null) {
			return;
		}

		// TODO: Make the pattern a pre-compiled constant, rather than compile on the fly
		typeSet.add(Pattern.class);
		code.line(0, "if (!Pattern.compile(\"" + pattern + "\").matcher(" + argumentName + ").matches()) {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " does not match pattern: '" + pattern + "', value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

	private void appendMax(IJavaCodeLines code, String argumentName) {
		String max = getValidator().getMax();
		if (max == null) {
			return;
		}

		int maxLength = Integer.parseInt(max);
		code.line(0, "if (" + argumentName + ".length() > " + maxLength + ") {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " too long, maximum of " + maxLength + " characters, value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

	private void appendMin(IJavaCodeLines code, String argumentName) {
		String min = getValidator().getMin();
		if (min == null) {
			return;
		}

		int minLength = Integer.parseInt(min);
		code.line(0, "if (" + argumentName + ".length() < " + minLength + ") {");
		code.line(1, "throw new IllegalArgumentException(\"" + argumentName + " too short, minimum of " + minLength + " characters, value: '\" + " + argumentName + " + \"'\");");
		code.line(0, "}");
	}

}
