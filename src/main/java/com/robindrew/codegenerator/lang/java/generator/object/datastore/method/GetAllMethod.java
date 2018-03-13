package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetAllMethod extends JavaMethod {

	public GetAllMethod(JavaModelDataStore dataStore) {
		super("getAll", dataStore.asList());
	}

}
