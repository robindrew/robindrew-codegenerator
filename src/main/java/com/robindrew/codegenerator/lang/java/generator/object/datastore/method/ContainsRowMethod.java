package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class ContainsRowMethod extends JavaMethod {

	public ContainsRowMethod(JavaModelDataStore dataStore, JavaModelBean row) {
		super("contains" + row.getType().getSimpleName(false), boolean.class);
		getParameters().add("row", row.getInterfaceType());
	}

}
