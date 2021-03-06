package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetByBeanMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class SqlGetByBeanMethod extends GetByBeanMethod {

	public SqlGetByBeanMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		JavaModelBean bean = dataStore.getElementBean();
		JavaModelBean keyBean = dataStore.getKeyBean();
		IJavaType parserType = bean.getResultSetParser().getType();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);
		getReferences().add(bean.getInterfaceType());

		setSqlContents(bean, keyBean, parserType);
	}

	private void setSqlContents(JavaModelBean bean, JavaModelBean keyBean, IJavaType parserType) {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.select();");

		// Columns
		boolean and = false;
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (and) {
				code.line("sql.sql(',');");
			}
			and = true;
			code.line("sql.column(\"" + toLower(field.getName()) + "\");");
		}
		code.line("sql.from(getTable());");

		// Where
		code.line("sql.where();");
		and = false;
		for (JavaModelBeanField field : keyBean.getFieldList()) {
			if (and) {
				code.line("sql.and();");
			}
			and = true;
			String columnName = toLower(field.getName());
			String columnValue = "key.get" + toUpper(field.getName()) + "()";
			if (field.getType().isEnum()) {
				columnValue = columnValue + ".ordinal()";
			}
			code.line("sql.column(\"" + columnName + "\").sql(\"=\").value(" + columnValue + ");");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("return getExecutor().get(sql, new " + parserType + "());");
		setContents(code);
	}
}
