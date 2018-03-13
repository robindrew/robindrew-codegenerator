package com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.derby.DerbySqlBuilder;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.derby.DerbyExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.SqlGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.methods.JavaMethodSet;

public class DerbyGenerator extends SqlGenerator {

	public DerbyGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	protected Class<? extends ISqlBuilder> getSqlBuilderType() {
		return DerbySqlBuilder.class;
	}

	@Override
	protected String getPrefix() {
		return "Derby";
	}

	@Override
	protected void addSpecialImplementations(JavaMethodSet methods) {
		methods.add(new DerbyExistsMethod());
	}

}
