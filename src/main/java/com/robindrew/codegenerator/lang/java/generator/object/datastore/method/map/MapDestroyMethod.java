package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.DestroyMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapDestroyMethod extends DestroyMethod {

	public MapDestroyMethod(JavaModelDataStore dataStore) {
		setOverride();

		JavaModelBeanField autoField = dataStore.getElementBean().getAutoIncrementField();

		JavaCodeLines code = new JavaCodeLines();
		code.line("map.clear();");

		// Auto increment (only reset on destroy)
		if (autoField != null) {
			code.line("autoIncrement.set(0);");
		}

		// Unique sets
		for (JavaModelBeanField field : dataStore.getUniqueFieldList()) {
			String lower = toLower(field.getName());
			code.line(lower + "Set.clear();");
		}

		setContents(code);
	}
}
