package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsRowMethod;

public class SqlContainsRowMethod extends ContainsRowMethod {

	public SqlContainsRowMethod(JavaModelDataStore dataStore, JavaModelBean row) {
		super(dataStore, row);
		setOverride();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);

		setSqlContents(row);
	}

	private void setSqlContents(JavaModelBean row) {

		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.selectCountAllFrom(getTable());");

		// Where
		code.line("sql.where();");

		boolean and = false;
		for (JavaModelBeanField field : row.getFieldList()) {
			if (and) {
				code.line("sql.and();");
			}
			and = true;
			code.line("sql.column(\"" + field.getName() + "\").sql(\"=\").value(row.get" + toUpper(field.getName()) + "());");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("return getExecutor().getCount(sql) > 0;");
		setContents(code);
	}
}
