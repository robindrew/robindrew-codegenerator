package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.List;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsMethod;

public class SqlContainsMethod extends ContainsMethod {

	public SqlContainsMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		List<JavaModelBeanField> fields = dataStore.getKeyBean().getFieldList();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);

		setSqlContents(fields);
	}

	private void setSqlContents(List<JavaModelBeanField> fields) {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.selectCountAllFrom(getTable());");

		// Where

		code.line("sql.where();");
		boolean and = false;
		for (JavaModelBeanField field : fields) {
			if (and) {
				code.line("sql.and();");
			}
			and = true;
			String lower = toLower(field.getName());
			String upper = toUpper(field.getName());
			code.line("sql.column(\"" + lower + "\").sql(\"=\").value(element.get" + upper + "());");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("return getExecutor().getCount(sql) > 0;");
		setContents(code);
	}
}
