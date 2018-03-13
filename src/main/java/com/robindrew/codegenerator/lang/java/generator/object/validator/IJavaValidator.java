package com.robindrew.codegenerator.lang.java.generator.object.validator;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public interface IJavaValidator {

	void appendMethodTo(IJavaCodeLines lines, String argumentName, IJavaTypeSet typeSet);

}
