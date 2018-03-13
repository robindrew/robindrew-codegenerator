package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveAllMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapRemoveAllMethod extends RemoveAllMethod {

	public MapRemoveAllMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		String dataStoreType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		code.line("for (" + dataStoreType + " element : elements) {");
		code.line(1, "remove(element);");
		code.line("}");
		setContents(code);
	}

}
