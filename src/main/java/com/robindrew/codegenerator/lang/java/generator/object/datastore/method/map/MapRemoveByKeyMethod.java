package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveByKeyMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapRemoveByKeyMethod extends RemoveByKeyMethod {

	private final JavaModelDataStoreKey key;

	public MapRemoveByKeyMethod(JavaModelDataStore dataStore, JavaModelBean bean, JavaModelDataStoreKey key) {
		super(dataStore, bean, key);
		this.key = key;
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
		JavaModelBean bean = key.getBean();
		boolean and = false;
		StringBuilder code = new StringBuilder();
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (and) {
				code.append(" && ");
			}
			and = true;
			new Equals().method1(field, name).field2(field).appendTo(code);
		}
		return code.toString();
	}

}
