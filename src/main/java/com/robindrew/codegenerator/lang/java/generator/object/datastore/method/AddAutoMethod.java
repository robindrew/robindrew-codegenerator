package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class AddAutoMethod extends JavaMethod {

	public AddAutoMethod(JavaModelDataStore dataStore) {
		super("addAuto");
		getParameters().add("element", dataStore.getElementBean().getInterfaceType());
	}

}
