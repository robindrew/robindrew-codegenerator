package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ClearMethod;

public class SqlClearMethod extends ClearMethod {

	public SqlClearMethod() {
		setOverride();
		getReferences().add(ISqlBuilder.class);
		setSqlContents();
	}

	private void setSqlContents() {
		SqlCodeLines code = new SqlCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.truncateTable(getTable());");

		// Execute
		code.emptyLine();
		code.line("// Execute");
		code.line("getExecutor().execute(sql);");
		setContents(code);
	}

}
