package com.robindrew.codegenerator.lang.java.generator.object.context;

import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.generator.object.stats.IJavaGeneratorStats;
import com.robindrew.codegenerator.lang.java.generator.object.validator.lookup.IJavaValidatorLookup;
import com.robindrew.codegenerator.lang.java.type.interfacemap.IJavaExtendsMap;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;

public interface IJavaContext {

	IJavaGeneratorStats getGeneratorStats();

	IJavaModelSet getModelSet();

	IJavaTypeResolver getResolver();

	IJavaExtendsMap getExtendsMap();

	IJavaValidatorLookup getValidatorLookup();

}
