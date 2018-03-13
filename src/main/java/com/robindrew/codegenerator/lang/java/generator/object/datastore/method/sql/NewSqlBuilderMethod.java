package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.sql;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class NewSqlBuilderMethod extends JavaMethod {

	public NewSqlBuilderMethod(Class<? extends ISqlBuilder> type) {
		super("newSqlBuilder", ISqlBuilder.class);
		getReferences().add(type);
		setContents(new JavaCodeLines("return new " + type.getSimpleName() + "();"));
	}

}
