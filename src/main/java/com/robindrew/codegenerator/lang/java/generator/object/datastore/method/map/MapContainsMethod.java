package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapContainsMethod extends ContainsMethod {

	public MapContainsMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();
		getReferences().add(ArrayList.class);

		String key = dataStore.getKeyBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		code.line(key + " key = getKey(element);");
		code.line("return map.containsKey(key);");
		setContents(code);
	}

}
