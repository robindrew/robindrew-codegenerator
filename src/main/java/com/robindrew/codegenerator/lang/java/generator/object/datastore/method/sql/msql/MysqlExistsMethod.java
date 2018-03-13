package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql.msql;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ExistsMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MysqlExistsMethod extends ExistsMethod {

	public MysqlExistsMethod() {
		setOverride();
		setSqlContents();
	}

	private void setSqlContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.emptyLine();
		code.line("// SQL");
		// SELECT COUNT(*) FROM information_schema.tables WHERE table_schema='mysql' AND table_name='user' LIMIT 1
		code.line("ISqlBuilder sql = newSqlBuilder();");
		code.line("sql.sql(\"SELECT COUNT(*) FROM information_schema.tables WHERE table_name=\").value(getTable()).sql(\" LIMIT 1\");");
		code.line("return getExecutor().getCount(sql) > 0;");
		setContents(code);
	}

}
