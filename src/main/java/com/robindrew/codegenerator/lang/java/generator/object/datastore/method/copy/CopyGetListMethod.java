package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;

public class CopyGetListMethod extends DelegateMethod {

	private final JavaModelDataStore dataStore;
	private final JavaModelBean bean;

	public CopyGetListMethod(IJavaMethod method, JavaModelDataStore dataStore, boolean identity) {
		this(method, dataStore, identity, dataStore.getElementBean());
	}

	public CopyGetListMethod(IJavaMethod method, JavaModelDataStore dataStore, boolean identity, JavaModelBean bean) {
		super(method);
		this.dataStore = dataStore;
		this.bean = bean;

		// Imports
		if (!identity) {
			getReferences().add(List.class);
			getReferences().add(ArrayList.class);
		}

		// Contents
		if (identity) {
			setContents(getIdentityContents());
		} else {
			setContents(getListContents());
		}
	}

	private IJavaCodeBlock getIdentityContents() {
		String returnType = bean.getInterfaceType().getSimpleName(true);
		String beanType = bean.getType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line(returnType + " returnValue = " + getDelegateCall() + ";");
		code.line("if (copyOnRead) {");
		code.line(1, "returnValue = new " + beanType + "(returnValue);");
		code.line("}");
		code.line("return returnValue;");
		return code;
	}

	private IJavaCodeBlock getListContents() {
		IJavaType rowType = bean.getInterfaceType();
		String listType = dataStore.asList(rowType).getSimpleName(true);
		String arrayListType = dataStore.asArrayList(rowType).getSimpleName(true);
		String interfaceType = rowType.getSimpleName(true);
		String beanType = bean.getType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line(listType + " list = " + getDelegateCall() + ";");
		code.line("if (copyOnRead) {");
		code.line(1, listType + " copy = new " + arrayListType + "(list.size());");
		code.line(1, "for (" + interfaceType + " dataStore : list) {");
		code.line(2, "copy.add(new " + beanType + "(dataStore));");
		code.line(1, "}");
		code.line(1, "list = copy;");
		code.line("}");
		code.line("return list;");
		return code;
	}
}
