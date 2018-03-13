package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class MapGetKeyMethod extends JavaMethod {

	public MapGetKeyMethod(JavaModelDataStore dataStore) {
		super("getKey", dataStore.getKeyBean().getInterfaceType());
		getParameters().add("element", dataStore.getElementBean().getInterfaceType());

		IJavaType keyType = dataStore.getKeyBean().getType();
		setContents(new JavaCodeLines("return new " + keyType.getSimpleName() + "(element);"));
	}

}
