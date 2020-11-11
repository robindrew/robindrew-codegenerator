package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.hsqldb;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlCodeLines;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlCreateMethod;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.SqlTypeDeclaration;

public class HsqldbCreateMethod extends SqlCreateMethod {

	public HsqldbCreateMethod(JavaModelDataStore dataStore) {
		super(dataStore);
	}

	@Override
	protected void setSqlContents() {
		SqlCodeLines code = new SqlCodeLines();
		code.line("if (exists()) {");
		code.line(1, "return;");
		code.line("}");
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.createTable(getTable());");
		code.line("sql.sql('(');");

		// Columns
		boolean comma = false;
		SqlTypeDeclaration declaration = getTypeDeclaration();
		List<JavaModelBeanField> uniqueList = new ArrayList<JavaModelBeanField>();
		for (JavaModelBeanField field : getDataStore().getElementBean().getFieldList()) {
			if (field.isUnique() && !field.isIdentity()) {
				uniqueList.add(field);
			}
			if (comma) {
				code.line("sql.sql(',');");
			}
			comma = true;
			code.line("sql.column(\"" + field.getName() + "\").sql(\" " + declaration.get(field) + "\");");

			// Not null?
			boolean notNull = isNotNull(field);
			if (notNull) {
				code.line("sql.notNull();");
			}
			if (field.isAutoIncrement()) {
				code.line("sql.autoIncrement();");
			}
		}

		// Primary Key
		if (!getDataStore().isAutoIncrement()) {
			code.line("sql.sql(',');");
			code.line("sql.sql(\"PRIMARY KEY (\");");
			comma = false;
			for (JavaModelBeanField field : getDataStore().getKeyBean().getFieldList()) {
				if (comma) {
					code.line("sql.sql(',');");
				}
				comma = true;
				code.line("sql.column(\"" + field.getName() + "\");");
			}
			code.line("sql.sql(')');");
		}

		// Unique Keys
		for (JavaModelBeanField field : uniqueList) {
			code.line("sql.sql(',');");
			code.line("sql.sql(\"UNIQUE (\");");
			code.line("sql.column(\"" + field.getName() + "\");");
			code.line("sql.sql(')');");
		}

		// Done
		code.line("sql.sql(')');");

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql);");
		setContents(code);
	}

}
