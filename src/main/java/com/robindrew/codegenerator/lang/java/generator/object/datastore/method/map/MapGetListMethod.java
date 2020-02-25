package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;
import java.util.Arrays;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class MapGetListMethod extends GetListMethod {

	public static String getCopyOf(JavaMethod method, IJavaType type) {
		method.getReferences().add(type);

		if (type.isArray()) {
			method.getReferences().add(Arrays.class);
			return "Arrays.copyOf(element, element.length)";
		}

		// Create new object with copy constructor
		return "new " + type.getSimpleName(true) + "(element)";
	}

	private final JavaModelBean row;
	private final JavaModelBean bean;
	private final JavaModelDataStore dataStore;

	public MapGetListMethod(JavaModelDataStore dataStore, JavaModelBean row) {
		super(dataStore, row);
		this.dataStore = dataStore;
		this.row = row;
		this.bean = dataStore.getElementBean();
		getReferences().add(ArrayList.class);
		setContents(getListContents());
	}

	private IJavaCodeBlock getListContents() {
		String beanListType = dataStore.asList(bean.getInterfaceType()).getSimpleName(true);
		String rowListType = dataStore.asList(row.getInterfaceType()).getSimpleName(true);
		String rowArrayListType = dataStore.asArrayList(row.getInterfaceType()).getSimpleName(true);
		String beanInterfaceType = bean.getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line(beanListType + " elements = getAll();");
		code.line(rowListType + " list = new " + rowArrayListType + "(elements.size());");
		code.line("for (" + beanInterfaceType + " element : elements) {");
		code.line(1, "list.add(" + getCopyOf(this, row.getType()) + ");");
		code.line("}");
		code.line("return list;");
		return code;
	}
}
