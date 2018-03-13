package com.robindrew.codegenerator.lang.java.generator.object.validator.lookup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.robindrew.codegenerator.lang.java.generator.object.validator.IJavaValidator;

public class JavaValidatorLookup implements IJavaValidatorLookup {

	private final Map<String, IJavaValidator> map = new ConcurrentHashMap<String, IJavaValidator>();

	@Override
	public IJavaValidator getValidator(String name) {
		IJavaValidator validator = map.get(name);
		if (validator == null) {
			throw new IllegalArgumentException("validator not found with name: " + name);
		}
		return validator;
	}

	@Override
	public void setValidator(String name, IJavaValidator validator) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name is empty");
		}
		if (validator == null) {
			throw new NullPointerException("validator");
		}
		if (map.containsKey(name)) {
			throw new IllegalArgumentException("duplicate validator for name: '" + name + "'");
		}
		map.put(name, validator);
	}

}
