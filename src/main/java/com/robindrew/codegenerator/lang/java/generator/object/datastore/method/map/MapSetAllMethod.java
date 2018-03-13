package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SetAllMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapSetAllMethod extends SetAllMethod {

	public MapSetAllMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		String dataStoreType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		code.line("for (" + dataStoreType + " element : elements) {");
		code.line(1, "set(element);");
		code.line("}");
		setContents(code);
	}

}
