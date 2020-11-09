package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveByBeanMethod;

public class SqlRemoveByBeanMethod extends RemoveByBeanMethod {

	public SqlRemoveByBeanMethod(JavaModelDataStore dataStore) {
		this(dataStore, dataStore.getKeyBean());
	}

	public SqlRemoveByBeanMethod(JavaModelDataStore dataStore, JavaModelBean bean) {
		super(dataStore, bean);

		setSqlContents(bean);
	}

	private void setSqlContents(JavaModelBean bean) {

		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.deleteFrom(table);");

		// Where
		code.line("sql.where();");
		boolean and = false;
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (and) {
				code.line("sql.and();");
			}
			and = true;
			String lower = toLower(field.getName());
			String getter = "get" + toUpper(field.getName()) + "()";
			code.line("sql.column(\"" + lower + "\").sql(\"=\").value(key." + getter + ");");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql);");
		setContents(code);
	}

}
