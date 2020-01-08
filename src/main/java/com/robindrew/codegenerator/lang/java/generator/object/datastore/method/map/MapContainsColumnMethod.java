package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsColumnMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class MapContainsColumnMethod extends ContainsColumnMethod {

	private JavaModelBeanField field;

	public MapContainsColumnMethod(JavaModelDataStore dataStore, JavaModelBeanField field) {
		super(field);
		setOverride();
		getReferences().add(ArrayList.class);
		this.field = field;

		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (element." + getEquals() + ") {");
		code.line(2, "return true;");
		code.line(1, "}");
		code.line("}");
		code.line("return false;");
		setContents(code);
	}

	private String getEquals() {
		StringBuilder code = new StringBuilder();
		if (field.getType().isBoolean()) {
			code.append("is");
		} else {
			code.append("get");
		}
		code.append(JavaName.toUpper(field.getName())).append("()");
		if (field.getType().isPrimitive()) {
			code.append(" == ").append(field.getName());
		} else {
			code.append(".equals(").append(field.getName()).append(")");
		}
		return code.toString();
	}

}