package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class RemoveMethod extends JavaMethod {

	public RemoveMethod(JavaModelDataStore dataStore) {
		super("remove");
		getParameters().add("element", dataStore.getElementBean().getInterfaceType());
	}

}
