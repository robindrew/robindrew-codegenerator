package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.CountColumnMethod;

public class SqlCountColumnMethod extends CountColumnMethod {

	public SqlCountColumnMethod(JavaModelBeanField field) {
		super(field);
		setOverride();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);

		setSqlContents(field);
	}

	private void setSqlContents(JavaModelBeanField field) {

		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.selectCountAllFrom(getTable());");

		// Where
		code.line("sql.where();");

		String columnName = field.getName();
		String columnValue = columnName;
		if (field.getType().isEnum()) {
			columnValue = columnValue + ".ordinal()";
		}
		code.line("sql.column(\"" + columnName + "\").sql(\"=\").value(" + columnValue + ");");

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("return getExecutor().getCount(sql);");
		setContents(code);
	}
}
