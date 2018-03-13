package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapAddMethod extends AddMethod {

	public MapAddMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		String key = dataStore.getKeyBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		code.line(key + " key = getKey(element);");
		code.line("map.put(key, element);");
		setContents(code);
	}

}
