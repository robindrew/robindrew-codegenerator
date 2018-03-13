package com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.hsqldb.HsqldbSqlBuilder;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.hsqldb.HsqldbCreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.hsqldb.HsqldbExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.SqlGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.methods.JavaMethodSet;

public class HsqldbGenerator extends SqlGenerator {

	public HsqldbGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	protected Class<? extends ISqlBuilder> getSqlBuilderType() {
		return HsqldbSqlBuilder.class;
	}

	@Override
	protected String getPrefix() {
		return "Hsqldb";
	}

	@Override
	protected void addSpecialImplementations(JavaMethodSet methods) {
		methods.add(new HsqldbExistsMethod());
		methods.add(new HsqldbCreateMethod(getDataStore()));
	}

}
