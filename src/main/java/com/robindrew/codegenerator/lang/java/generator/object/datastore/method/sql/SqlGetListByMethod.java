package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListByMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class SqlGetListByMethod extends GetListByMethod {

	public SqlGetListByMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter, JavaModelBean bean, boolean unique) {
		super(dataStore, parameter, bean, unique);
		setOverride();

		IJavaType parserType = bean.getResultSetParser().getType();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);
		getReferences().add(parserType);

		setSqlContents(parameter, bean, parserType, unique);
	}

	private void setSqlContents(JavaModelBeanField parameter, JavaModelBean bean, IJavaType parserType, boolean unique) {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.select();");

		// Columns
		boolean comma = false;
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (comma) {
				code.line("sql.sql(',');");
			}
			comma = true;
			code.line("sql.column(\"" + toLower(field.getName()) + "\");");
		}
		code.line("sql.from(getTable());");

		// Where
		code.line("sql.where();");

		String columnName = toLower(parameter.getName());
		String columnValue = toLower(parameter.getName());
		if (parameter.getType().isEnum()) {
			columnValue = columnValue + ".ordinal()";
		}
		code.line("sql.column(\"" + columnName + "\").sql(\"=\").value(" + columnValue + ");");

		// Execute
		code.emptyLine();
		code.line("// Execute");
		if (unique) {
			code.line("return getExecutor().get(sql, new " + parserType + "());");
		} else {
			code.line("return getExecutor().getList(sql, new " + parserType + "());");
		}
		setContents(code);
	}
}
