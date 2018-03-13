package com.robindrew.codegenerator.lang.java.generator.object.context;

import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.stats.IJavaGeneratorStats;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.IJavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.type.interfacemap.IJavaExtendsMap;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public class JavaContext implements IJavaContext {

	private final IJavaModelSet modelSet;
	private final IJavaTypeResolver resolver;
	private final IJavaExtendsMap extendsMap;
	private final IJavaValidatorLookup validatorLookup;
	private final IJavaGeneratorStats generatorStats;

	public JavaContext(IJavaModelSet modelSet, IJavaTypeResolver resolver, IJavaExtendsMap extendsMap, IJavaValidatorLookup validatorLookup, IJavaGeneratorStats generatorStats) {
		if (modelSet == null) {
			throw new NullPointerException("modelSet");
		}
		if (resolver == null) {
			throw new NullPointerException("resolver");
		}
		if (extendsMap == null) {
			throw new NullPointerException("extendsMap");
		}
		if (generatorStats == null) {
			throw new NullPointerException("generatorStats");
		}
		this.modelSet = modelSet;
		this.resolver = resolver;
		this.extendsMap = extendsMap;
		this.validatorLookup = validatorLookup;
		this.generatorStats = generatorStats;
	}

	@Override
	public IJavaModelSet getModelSet() {
		return modelSet;
	}

	@Override
	public IJavaTypeResolver getResolver() {
		return resolver;
	}

	@Override
	public IJavaExtendsMap getExtendsMap() {
		return extendsMap;
	}

	@Override
	public IJavaValidatorLookup getValidatorLookup() {
		return validatorLookup;
	}

	@Override
	public IJavaGeneratorStats getGeneratorStats() {
		return generatorStats;
	}

}
