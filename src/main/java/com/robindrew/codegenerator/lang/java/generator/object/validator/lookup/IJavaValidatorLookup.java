package com.robindrew.codegenerator.lang.java.generator.object.validator.lookup;

import com.robindrew.codegenerator.lang.java.generator.object.validator.IJavaValidator;

public interface IJavaValidatorLookup {

	IJavaValidator getValidator(String name);

	void setValidator(String name, IJavaValidator validator);

}
