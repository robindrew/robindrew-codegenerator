package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ClearMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class MapClearMethod extends ClearMethod {

	public MapClearMethod(JavaModelDataStore dataStore) {
		setOverride();

		IJavaCodeLines code = new JavaCodeLines();
		code.line("map.clear();");

		// Unique sets
		for (JavaModelBeanField field : dataStore.getUniqueFieldList()) {
			String lower = JavaName.toLower(field.getName());
			code.line(lower + "Set.clear();");
		}
		setContents(code);
	}

}
