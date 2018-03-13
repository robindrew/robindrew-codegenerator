package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapGetMethod extends GetMethod {

	public MapGetMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();
		getReferences().add(ArrayList.class);

		String key = dataStore.getKeyBean().getType().getSimpleName(true);

		setContents(new JavaCodeLines("return map.get(new " + key + "(" + getNameList(getFields()) + "));"));
	}

	private String getNameList(List<JavaModelBeanField> fields) {
		StringBuilder names = new StringBuilder();
		boolean comma = false;
		for (JavaModelBeanField field : fields) {
			if (comma) {
				names.append(',');
			}
			comma = true;
			names.append(field.getName());
		}
		return names.toString();
	}

}
