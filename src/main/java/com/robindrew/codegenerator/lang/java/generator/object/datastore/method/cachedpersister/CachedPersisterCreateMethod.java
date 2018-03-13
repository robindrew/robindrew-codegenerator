package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.CreateMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class CachedPersisterCreateMethod extends CreateMethod implements ICachedPersisterMethod {

	public CachedPersisterCreateMethod() {
		setCreateContents();
		setOverride();
	}

	private void setCreateContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("if (!cache.exists()) {");
		code.line(1, "cache.create();");
		code.line("}");
		code.line("if (!persister.exists()) {");
		code.line(1, "persister.create();");
		code.line("}");
		code.emptyLine();
		code.line("// Populate Cache");
		code.line("cache.clear();");
		code.line("cache.addAll(persister.getAll());");
		setContents(code);
	}

}
