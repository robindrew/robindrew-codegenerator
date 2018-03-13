package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetAllMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapGetAllMethod extends GetAllMethod {

	public MapGetAllMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();
		getReferences().add(ArrayList.class);

		String type = dataStore.getElementBean().getInterfaceType().getSimpleName();
		setContents(new JavaCodeLines("return new ArrayList<" + type + ">(map.values());"));
	}

}
