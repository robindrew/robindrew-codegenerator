package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.h2;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ExistsMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class H2ExistsMethod extends ExistsMethod {

	public H2ExistsMethod() {
		setOverride();
		setSqlContents();
	}

	private void setSqlContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.emptyLine();
		code.line("// SQL");
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.sql(\"SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME=\").value(getTable());");
		code.line("return getExecutor().getCount(sql) > 0;");
		setContents(code);
	}

}
