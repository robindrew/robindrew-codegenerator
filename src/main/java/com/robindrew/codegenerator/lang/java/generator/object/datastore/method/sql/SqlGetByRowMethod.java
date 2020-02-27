package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetByRowMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class SqlGetByRowMethod extends GetByRowMethod {

	public SqlGetByRowMethod(JavaModelDataStore dataStore, JavaModelBean bean, JavaModelBean row) {
		super(dataStore, bean, row);

		setSqlContents(bean, row);
	}

	private void setSqlContents(JavaModelBean bean, JavaModelBean row) {
		IJavaType parserType = bean.getResultSetParser().getType();

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
		for (JavaModelBeanField field : row.getFieldList()) {
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
		code.line("return getExecutor().getList(sql, new " + parserType + "());");
		setContents(code);
	}

}
