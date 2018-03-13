package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class ExistsMethod extends JavaMethod {

	public ExistsMethod() {
		super("exists", boolean.class);
	}

	public ExistsMethod setDefaultContents() {
		setContents(new JavaCodeLines("return true;"));
		return this;
	}

}
