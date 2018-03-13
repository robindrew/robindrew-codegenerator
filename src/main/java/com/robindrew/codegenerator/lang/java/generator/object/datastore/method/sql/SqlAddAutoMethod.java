package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import static com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlCodeLines.questions;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.List;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.executor.ISqlValues;
import com.robindrew.codegenerator.api.sql.executor.SqlValues;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAutoMethod;

public class SqlAddAutoMethod extends AddAutoMethod {

	public SqlAddAutoMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		JavaModelBean bean = dataStore.getElementBean();
		List<JavaModelBeanField> fields = bean.getFieldList();

		getReferences().add(ISqlBuilder.class);
		getReferences().add(ISqlValues.class);
		getReferences().add(SqlValues.class);
		getReferences().add(bean.getInterfaceType());

		setSqlContents(fields);
	}

	private void setSqlContents(List<JavaModelBeanField> fields) {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.insertInto(getTable());");

		// Columns
		JavaModelBeanField auto = null;
		boolean comma = false;
		code.line("sql.sql('(');");
		for (JavaModelBeanField field : fields) {
			if (field.isAutoIncrement()) {
				auto = field;
				continue;
			}
			if (comma) {
				code.line("sql.sql(',');");
			}
			comma = true;
			code.line("sql.column(\"" + toLower(field.getName()) + "\");");
		}
		code.line("sql.sql(\") VALUES (" + questions(fields.size() - 1) + ")\");");

		// Values
		code.emptyLine();
		code.line("// Values");
		code.line("ISqlValues values = new SqlValues();");
		for (JavaModelBeanField field : fields) {
			if (field.isAutoIncrement()) {
				continue;
			}
			code.line("values.add(element.get" + toUpper(field.getName()) + "());");
		}

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("int " + auto.getName() + " = getExecutor().executeAutoIncrement(sql, values);");
		code.line("element.set" + toUpper(auto.getName()) + "(" + auto.getName() + ");");
		setContents(code);
	}
}
