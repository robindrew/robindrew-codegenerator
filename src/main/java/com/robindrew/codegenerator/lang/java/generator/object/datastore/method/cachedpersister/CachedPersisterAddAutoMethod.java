package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister;

import com.robindrew.codegenerator.api.datastore.IDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAutoMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class CachedPersisterAddAutoMethod extends AddAutoMethod implements ICachedPersisterMethod {

	public CachedPersisterAddAutoMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		getComment().line("Note: this method will not update the element parameter correctly");
		getComment().line("if wrapped in a copy-on-write {@link IDataStore}.");
		getReferences().add(IDataStore.class);
		setMethodContents();
		setOverride();
	}

	private void setMethodContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("cache.addAuto(element);");
		code.line("persister.add(element);");
		setContents(code);
	}

}
