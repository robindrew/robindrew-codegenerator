package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.CountColumnMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapCountColumnMethod extends CountColumnMethod {

	public MapCountColumnMethod(JavaModelDataStore dataStore, JavaModelBeanField field) {
		super(field);
		setOverride();
		getReferences().add(ArrayList.class);

		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("int count = 0;");
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (" + new Equals().method1(field, "element").field2(field) + ") {");
		code.line(2, "count++;");
		code.line(1, "}");
		code.line("}");
		code.line("return count;");
		setContents(code);
	}

}
