package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class SetMethod extends JavaMethod {

	public SetMethod(JavaModelDataStore dataStore) {
		super("set");
		getParameters().add("element", dataStore.getElementBean().getInterfaceType());
	}

}
