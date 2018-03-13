package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.DestroyMethod;

public class SqlDestroyMethod extends DestroyMethod {

	public SqlDestroyMethod() {
		setOverride();
		getReferences().add(ISqlBuilder.class);
		setSqlContents();
	}

	private void setSqlContents() {
		SqlCodeLines code = new SqlCodeLines();
		code.line("if (!exists()) {");
		code.line(1, "return;");
		code.line("}");
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.dropTable(getTable());");

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql);");
		setContents(code);
	}

}
