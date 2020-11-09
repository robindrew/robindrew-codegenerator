package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveByBeanMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class MapRemoveByBeanMethod extends RemoveByBeanMethod {

	public MapRemoveByBeanMethod(JavaModelDataStore dataStore) {
		this(dataStore, dataStore.getKeyBean());
	}

	public MapRemoveByBeanMethod(JavaModelDataStore dataStore, JavaModelBean bean) {
		super(dataStore);
		setOverride();

		// Contents
		setContents(getRemoveContents());
	}

	private IJavaCodeBlock getRemoveContents() {
		String type = getDataStore().getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("List<" + type + "> elements = new ArrayList<>(map.values());");
		code.line("for (" + type + " element : elements) {");
		code.line(1, "if (" + getEquals("element") + ") {");
		code.line(2, "remove(element);");
		code.line(1, "}");
		code.line("}");
		return code;
	}

	private String getEquals(String name) {
		boolean and = false;
		StringBuilder code = new StringBuilder();
		for (JavaModelBeanField field : getBean().getFieldList()) {
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
		boolean isBoolean = parameter.getType().isBoolean();
		code.append(JavaName.toGetter(parameter.getName(), isBoolean));
		if (parameter.getType().isPrimitive()) {
			code.append(" == key.").append(JavaName.toGetter(parameter.getName(), isBoolean));
		} else {
			code.append(".equals(key.").append(JavaName.toGetter(parameter.getName(), isBoolean)).append(")");
		}
	}

}
