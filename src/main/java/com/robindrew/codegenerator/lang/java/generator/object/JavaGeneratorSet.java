package com.robindrew.codegenerator.lang.java.generator.object;

import java.util.ArrayList;
import java.util.List;

public class JavaGeneratorSet implements IJavaGenerator {

	private final List<IJavaGenerator> generatorList = new ArrayList<IJavaGenerator>();

	public void add(IJavaGenerator generator) {
		if (generator == null) {
			throw new NullPointerException("generator");
		}
		generatorList.add(generator);
	}

	@Override
	public void registerPrimaryTypes() {
		for (IJavaGenerator generator : generatorList) {
			generator.registerPrimaryTypes();
		}
	}

	@Override
	public void registerSecondaryTypes() {
		for (IJavaGenerator generator : generatorList) {
			generator.registerSecondaryTypes();
		}
	}

	@Override
	public void verifyReferencedTypes() {
		for (IJavaGenerator generator : generatorList) {
			generator.verifyReferencedTypes();
		}
	}

	@Override
	public void generate() {
		for (IJavaGenerator generator : generatorList) {
			generator.generate();
		}
	}

}
