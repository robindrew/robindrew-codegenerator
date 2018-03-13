package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetByKeyMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class MapGetByKeyMethod extends GetByKeyMethod {

	private final JavaModelDataStoreKey key;

	public MapGetByKeyMethod(JavaModelDataStore dataStore, JavaModelBean bean, JavaModelDataStoreKey key) {
		super(dataStore, bean, key);
		this.key = key;
		setOverride();

		// Imports
		boolean identity = key.isUnique();
		if (!identity) {
			getReferences().add(ArrayList.class);
		}

		// Contents
		JavaModelBean keyBean = key.getBean();
		if (identity) {
			setContents(getIdentityContents(keyBean));
		} else {
			setContents(getListContents(keyBean));
		}
	}

	private IJavaCodeBlock getIdentityContents(JavaModelBean bean) {
		boolean newInstance = !getBean().equals(bean);
		String type = getDataStore().getElementBean().getInterfaceType().getSimpleName(true);
		String newType = getBean().getType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (" + getEquals("element") + ") {");
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
		boolean newInstance = !getBean().equals(bean);
		String type = getDataStore().getElementBean().getInterfaceType().getSimpleName(true);
		String newType = getBean().getType().getSimpleName(true);
		String returnType = getBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("List<" + returnType + "> list = new ArrayList<" + returnType + ">();");
		code.line("for (" + type + " element : map.values()) {");
		code.line(1, "if (" + getEquals("element") + ") {");
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

	private String getEquals(String name) {
		JavaModelBean bean = key.getBean();
		boolean and = false;
		StringBuilder code = new StringBuilder();
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (and) {
				code.append(" && ");
			}
			and = true;
			code.append(name).append('.');
			getEquals(field, code);
		}
		return code.toString();
	}

	private void getEquals(JavaModelBeanField parameter, StringBuilder code) {
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
	}

}
