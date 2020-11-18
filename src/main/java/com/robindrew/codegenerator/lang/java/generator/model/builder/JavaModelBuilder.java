package com.robindrew.codegenerator.lang.java.generator.model.builder;

import com.robindrew.codegenerator.model.object.builder.ModelBuilder;

public class JavaModelBuilder {

	private final ModelBuilder builder;

	public JavaModelBuilder(ModelBuilder builder) {
		if (builder == null) {
			throw new NullPointerException("bean");
		}
		this.builder = builder;
	}

	public ModelBuilder get() {
		return builder;
	}

	@Override
	public String toString() {
		return builder.getName() + " (" + builder.getBean() + ")";
	}

}
