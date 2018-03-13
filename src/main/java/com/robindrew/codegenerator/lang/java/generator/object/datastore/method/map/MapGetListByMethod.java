package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListByMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class MapGetListByMethod extends GetListByMethod {

	private final JavaModelDataStore dataStore;
	private final JavaModelBeanField parameter;

	public MapGetListByMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter, JavaModelBean bean, boolean identity) {
		super(dataStore, parameter, bean, identity);
		this.dataStore = dataStore;
		this.parameter = parameter;
		setOverride();

		// Imports
		if (!identity) {
			getReferences().add(ArrayList.class);
		}

		// Contents
		if (identity) {
			setContents(getIdentityContents(bean));
		} else {
			setContents(getListContents(bean));
		}
	}

	private IJavaCodeBlock getIdentityContents(JavaModelBean bean) {
		boolean newInstance = !dataStore.getElementBean().equals(bean);
		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);
		String newType = bean.getType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (element." + getEquals() + ") {");
		if (newInstance) {
			code.line(2, "return new " + newType + "(element);");
		} else {
			code.line(2, "return element;");
		}
		code.line(1, "}");
		code.line("}");
		code.line("return null;");
		return code;
	}

	private IJavaCodeBlock getListContents(JavaModelBean bean) {
		boolean newInstance = !dataStore.getElementBean().equals(bean);
		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);
		String newType = bean.getType().getSimpleName(true);
		String returnType = bean.getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("List<" + returnType + "> list = new ArrayList<" + returnType + ">();");
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (element." + getEquals() + ") {");
		if (newInstance) {
			code.line(2, "list.add(new " + newType + "(element));");
		} else {
			code.line(2, "list.add(element);");
		}
		code.line(1, "}");
		code.line("}");
		code.line("return list;");
		return code;
	}

	private String getEquals() {
		StringBuilder code = new StringBuilder();
		if (parameter.getType().isBoolean()) {
			code.append("is");
		} else {
			code.append("get");
		}
		code.append(JavaName.toUpper(parameter.getName())).append("()");
		if (parameter.getType().isPrimitive()) {
			code.append(" == ").append(parameter.getName());
		} else {
			code.append(".equals(").append(parameter.getName()).append(")");
		}
		return code.toString();
	}

}
