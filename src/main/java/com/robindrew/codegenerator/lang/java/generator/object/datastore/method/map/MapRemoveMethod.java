package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapRemoveMethod extends RemoveMethod {

	public MapRemoveMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		String key = dataStore.getKeyBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		code.line(key + " key = getKey(element);");
		code.line("map.remove(key);");

		// Unique sets
		for (JavaModelBeanField field : dataStore.getUniqueFieldList()) {
			String lower = toLower(field.getName());
			String upper = toUpper(field.getName());
			code.line(lower + "Set.remove(element.get" + upper + "());");
		}

		setContents(code);
	}

}
