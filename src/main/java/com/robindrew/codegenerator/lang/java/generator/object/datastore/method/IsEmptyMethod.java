package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class IsEmptyMethod extends JavaMethod {

	public IsEmptyMethod() {
		super("isEmpty", boolean.class);
	}

	public IsEmptyMethod setDefaultContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("return size() == 0;");
		setContents(code);
		return this;
	}

}
