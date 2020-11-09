package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveByKeyMethod;

public class SqlRemoveByKeyMethod extends RemoveByKeyMethod {

	public SqlRemoveByKeyMethod(JavaModelDataStore dataStore, JavaModelBean bean, JavaModelDataStoreKey key) {
		super(dataStore, bean, key);

		setSqlContents(bean, key);
	}

	private void setSqlContents(JavaModelBean bean, JavaModelDataStoreKey key) {

		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.deleteFrom(table);");

		// Where
		code.line("sql.where();");
		boolean and = false;
		for (JavaModelBeanField field : key.getBean().getFieldList()) {
			if (and) {
				code.line("sql.and();");
			}
			and = true;
			String lower = toLower(field.getName());
			code.line("sql.column(\"" + lower + "\").sql(\"=\").value(" + lower + ");");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql);");
		setContents(code);
	}

}
