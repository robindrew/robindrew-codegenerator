package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveMethod;

public class SqlRemoveMethod extends RemoveMethod {

	public SqlRemoveMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		JavaModelBean bean = dataStore.getKeyBean();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);

		setSqlContents(bean);
	}

	private void setSqlContents(JavaModelBean bean) {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.deleteFrom(getTable());");

		// Where
		code.line("sql.where();");
		boolean and = false;
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (and) {
				code.line("sql.and();");
			}
			and = true;
			code.line("sql.column(\"" + toLower(field.getName()) + "\").sql(\"=?\");");
		}

		// Values
		code.emptyLine();
		code.line("// Values");
		code.line("ISqlValues values = new SqlValues();");
		for (JavaModelBeanField field : bean.getFieldList()) {
			code.line("values.add(element.get" + toUpper(field.getName()) + "());");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql, values);");
		setContents(code);
	}

}
