package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAllMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapAddAllMethod extends AddAllMethod {

	public MapAddAllMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		String dataStoreType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		code.line("for (" + dataStoreType + " element : elements) {");
		code.line(1, "add(element);");
		code.line("}");
		setContents(code);
	}

}
