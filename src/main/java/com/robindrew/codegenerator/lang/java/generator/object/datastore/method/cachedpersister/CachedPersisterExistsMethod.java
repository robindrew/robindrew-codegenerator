package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister;

import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ExistsMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class CachedPersisterExistsMethod extends ExistsMethod implements ICachedPersisterMethod {

	public CachedPersisterExistsMethod() {
		setExistsContents();
		setOverride();
	}

	private void setExistsContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("return cache.exists() && persister.exists();");
		setContents(code);
	}

}
