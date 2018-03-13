package com.robindrew.codegenerator.lang.java.generator.object.alias;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.model.object.alias.ModelAlias;
import com.robindrew.codegenerator.setup.ISetup;
import com.robindrew.common.util.Java;

public class JavaAliasGenerator extends JavaGenerator {

	private final ModelAlias alias;

	public JavaAliasGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelAlias alias) {
		super(setup, context, model);
		if (alias == null) {
			throw new NullPointerException("alias");
		}
		this.alias = alias;
	}

	@Override
	public void registerPrimaryTypes() {
		try {
			String name = alias.getName();
			Class<?> clazz = Class.forName(alias.getType());
			getContext().getResolver().registerAlias(clazz, name);
		} catch (Exception e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public void verifyReferencedTypes() {
		// Nothing to do
	}

	@Override
	public void generate() {
		// Nothing to do
	}

}
