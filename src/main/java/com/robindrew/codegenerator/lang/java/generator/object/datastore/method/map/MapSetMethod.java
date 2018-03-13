package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;
import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.SetMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapSetMethod extends SetMethod {

	public MapSetMethod(JavaModelDataStore dataStore) {
		super(dataStore);
		setOverride();

		String replacedType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);
		String keyType = dataStore.getKeyBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line(keyType + " key = getKey(element);");

		// Check if the key is replaced
		code.line(replacedType + " replaced = map.get(key);");
		code.line("if (replaced == null) {");
		code.line(1, "return;");
		code.line("}");

		// Update unique sets
		for (JavaModelBeanField field : dataStore.getUniqueFieldList()) {
			String lower = toLower(field.getName());
			String upper = toUpper(field.getName());
			code.line("if (" + getEquals(lower, upper, field.getType()) + ") {");
			code.line(1, lower + "Set.remove(replaced.get" + upper + "());");
			code.line(1, lower + "Set.add(element.get" + upper + "());");
			code.line("}");
		}

		code.line("map.put(key, element);");
		setContents(code);
	}

	private String getEquals(String lower, String upper, IJavaType type) {
		if (type.isPrimitive()) {
			return "element.get" + upper + "() != replaced.get" + upper + "()";
		} else {
			return "!element.get" + upper + "().equals(replaced.get" + upper + "())";
		}
	}

}
