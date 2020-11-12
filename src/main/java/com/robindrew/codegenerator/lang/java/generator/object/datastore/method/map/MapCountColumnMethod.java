package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.CountColumnMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapCountColumnMethod extends CountColumnMethod {

	private JavaModelBeanField field;

	public MapCountColumnMethod(JavaModelDataStore dataStore, JavaModelBeanField field) {
		super(field);
		setOverride();
		getReferences().add(ArrayList.class);
		this.field = field;

		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("int count = 0;");
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (element." + getEquals() + ") {");
		code.line(2, "count++;");
		code.line(1, "}");
		code.line("}");
		code.line("return count;");
		setContents(code);
	}

	private String getEquals() {
		StringBuilder code = new StringBuilder();
		code.append("get").append(toUpper(field.getName())).append("()");
		if (field.getType().isPrimitive()) {
			code.append(" == ").append(field.getName());
		} else {
			code.append(".equals(").append(field.getName()).append(")");
		}
		return code.toString();
	}

}
