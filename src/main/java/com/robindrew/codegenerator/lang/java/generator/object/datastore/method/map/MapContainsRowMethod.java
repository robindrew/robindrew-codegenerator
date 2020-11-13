package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

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
			code.line(1, "if (" + new Equals().not().method1(field, "element").method2(field, "row") + ") {");
			code.line(2, "continue;");
			code.line(1, "}");
		}
		code.line(1, "return true;");
		code.line("}");
		code.line("return false;");
		return code;
	}

}
