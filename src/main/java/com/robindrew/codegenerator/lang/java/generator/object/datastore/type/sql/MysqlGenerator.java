package com.robindrew.codegenerator.lang.java.generator.object.datastore.type.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.mysql.MysqlSqlBuilder;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.JavaDataStoreGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.msql.MysqlExistsMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.type.SqlGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.methods.JavaMethodSet;

public class MysqlGenerator extends SqlGenerator {

	public MysqlGenerator(JavaDataStoreGenerator generator) {
		super(generator);
	}

	@Override
	protected Class<? extends ISqlBuilder> getSqlBuilderType() {
		return MysqlSqlBuilder.class;
	}

	@Override
	protected String getPrefix() {
		return "Mysql";
	}

	@Override
	protected void addSpecialImplementations(JavaMethodSet methods) {
		methods.add(new MysqlExistsMethod());
	}

}
