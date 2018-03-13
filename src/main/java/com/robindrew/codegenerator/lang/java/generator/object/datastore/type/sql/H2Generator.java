package com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.h2.H2SqlBuilder;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.h2.H2ExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.SqlGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.methods.JavaMethodSet;

public class H2Generator extends SqlGenerator {

	public H2Generator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	protected Class<? extends ISqlBuilder> getSqlBuilderType() {
		return H2SqlBuilder.class;
	}

	@Override
	protected String getPrefix() {
		return "H2";
	}

	@Override
	protected void addSpecialImplementations(JavaMethodSet methods) {
		methods.add(new H2ExistsMethod());
	}

}
