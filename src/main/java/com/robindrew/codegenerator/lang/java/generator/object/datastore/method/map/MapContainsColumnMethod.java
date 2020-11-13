package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsColumnMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapContainsColumnMethod extends ContainsColumnMethod {

	public MapContainsColumnMethod(JavaModelDataStore dataStore, JavaModelBeanField field) {
		super(field);
		setOverride();
		getReferences().add(ArrayList.class);

		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (" + new Equals().method1(field, "element").field2(field).get() + ") {");
		code.line(2, "return true;");
		code.line(1, "}");
		code.line("}");
		code.line("return false;");
		setContents(code);
	}

}
