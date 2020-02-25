package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.ContainsRowMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapContainsRowMethod extends ContainsRowMethod {

	private final JavaModelBean row;
	private final JavaModelBean bean;
	private final boolean mapKey;

	public MapContainsRowMethod(JavaModelDataStore dataStore) {
		this(dataStore, dataStore.getKeyBean(), true);
	}

	public MapContainsRowMethod(JavaModelDataStore dataStore, JavaModelBean row) {
		this(dataStore, row, false);
	}

	public MapContainsRowMethod(JavaModelDataStore dataStore, JavaModelBean row, boolean mapKey) {
		super(dataStore, row);
		setOverride();
		this.row = row;
		this.mapKey = mapKey;
		this.bean = dataStore.getElementBean();
		getReferences().add(ArrayList.class);
		setContents(getListContents());
	}

	private IJavaCodeBlock getListContents() {
		String beanInterfaceType = bean.getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		if (mapKey) {
			code.line("return getBy" + row.getType().getSimpleName(false) + "(row) != null;");
			return code;
		}

		code.line("for (" + beanInterfaceType + " element : map.values()) {");
		for (JavaModelBeanField field : row.getFieldList()) {
			code.line(1, "if (" + getNotEquals(field) + ") {");
			code.line(2, "continue;");
			code.line(1, "}");
		}
		code.line(1, "return true;");
		code.line("}");
		code.line("return false;");
		return code;
	}

	private String getNotEquals(JavaModelBeanField field) {
		StringBuilder code = new StringBuilder();
		String getter = "get" + toUpper(field.getName()) + "()";
		if (field.getType().isPrimitive()) {
			code.append("element.").append(getter).append(" != ").append("row.").append(getter);
		} else {
			code.append("!element.").append(getter).append(".equals(").append("row.").append(getter).append(")");
		}
		return code.toString();
	}

}
