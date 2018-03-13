package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class ContainsMethod extends JavaMethod {

	public ContainsMethod(JavaModelDataStore dataStore) {
		super("contains", boolean.class);
		getParameters().add("element", dataStore.getElementBean().getInterfaceType());
	}

}
