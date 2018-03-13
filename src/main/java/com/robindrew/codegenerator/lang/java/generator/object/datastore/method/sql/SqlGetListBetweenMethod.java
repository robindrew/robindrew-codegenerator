package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListBetweenMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class SqlGetListBetweenMethod extends GetListBetweenMethod {

	public SqlGetListBetweenMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter) {
		super(dataStore, parameter);
		setOverride();

		JavaModelBean bean = dataStore.getElementBean();
		IJavaType parserType = bean.getResultSetParser().getType();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);
		getReferences().add(parserType);

		setSqlContents(parameter, bean, parserType);
	}

	private void setSqlContents(JavaModelBeanField parameter, JavaModelBean bean, IJavaType parserType) {
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
		code.line("if (from != null) {");
		code.line(1, "sql.column(\"" + parameter.getName() + "\").sql(\">=\").value(from);");
		code.line("}");
		code.line("if (from != null && to != null) {");
		code.line(1, "sql.and();");
		code.line("}");
		code.line("if (to != null) {");
		code.line(1, "sql.column(\"" + parameter.getName() + "\").sql(\"<=\").value(to);");
		code.line("}");

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("return getExecutor().getList(sql, new " + parserType + "());");
		setContents(code);
	}
}
