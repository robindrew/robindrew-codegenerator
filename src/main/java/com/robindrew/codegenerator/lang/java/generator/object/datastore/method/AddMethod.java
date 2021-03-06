package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class AddMethod extends JavaMethod {

	public AddMethod(JavaModelDataStore dataStore) {
		super("add");
		getParameters().add("element", dataStore.getElementBean().getInterfaceType());
	}

}
