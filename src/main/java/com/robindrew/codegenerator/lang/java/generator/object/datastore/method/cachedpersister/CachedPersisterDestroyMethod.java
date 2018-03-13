package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.DestroyMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class CachedPersisterDestroyMethod extends DestroyMethod implements ICachedPersisterMethod {

	public CachedPersisterDestroyMethod() {
		setDestroyContents();
		setOverride();
	}

	private void setDestroyContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("if (cache.exists()) {");
		code.line(1, "cache.destroy();");
		code.line("}");
		code.line("");
		code.line("if (persister.exists()) {");
		code.line(1, "persister.destroy();");
		code.line("}");
		setContents(code);
	}

}
