package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.AddAutoMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapAddAutoMethod extends AddAutoMethod {

	public MapAddAutoMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		JavaModelBeanField autoField = dataStore.getElementBean().getAutoIncrementField();
		String type = dataStore.getKeyBean().getInterfaceType().getSimpleName(true);

		JavaCodeLines code = new JavaCodeLines();
		checkUniqueSets(code, dataStore);
		code.line("// We loop until we find a key we can add ...");
		code.line("while (true) {");
		code.line(1, "element.set" + toUpper(autoField.getName()) + "(autoIncrement.addAndGet(1));");
		code.line(1, type + " key = getKey(element);");
		code.line(1, "if (!map.containsKey(key)) {");
		code.line(2, "map.put(key, element);");
		code.line(2, "return;");
		code.line(1, "}");
		code.line("}");
		setContents(code);
	}

	private void checkUniqueSets(JavaCodeLines code, JavaModelDataStore dataStore) {
		for (JavaModelBeanField field : dataStore.getUniqueFieldList()) {
			String lower = toLower(field.getName());
			String upper = toUpper(field.getName());
			code.line("if (" + lower + "Set.contains(element.get" + upper + "())) {");
			code.line(1, "throw new IllegalStateException(\"" + lower + " already exists: \" + element.get" + upper + "());");
			code.line("}");
		}
	}

}
