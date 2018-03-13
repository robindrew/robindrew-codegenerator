package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;

public class CopySetListMethod extends DelegateMethod {

	private final JavaModelDataStore dataStore;

	public CopySetListMethod(IJavaMethod method, JavaModelDataStore dataStore) {
		super(method);
		this.dataStore = dataStore;

		// Imports
		getReferences().add(List.class);
		getReferences().add(ArrayList.class);
		getReferences().add(Collection.class);

		// Contents
		setContents(getListContents());
	}

	private IJavaCodeBlock getListContents() {
		String listType = dataStore.asList().getSimpleName(true);
		String arrayListType = dataStore.asArrayList().getSimpleName(true);
		String interfaceType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);
		String beanType = dataStore.getElementBean().getType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("if (copyOnWrite) {");
		code.line(1, listType + " copy = new " + arrayListType + "(elements.size());");
		code.line(1, "for (" + interfaceType + " element : elements) {");
		code.line(2, "copy.add(new " + beanType + "(element));");
		code.line(1, "}");
		code.line(1, "elements = copy;");
		code.line("}");
		code.line(getDelegateCall() + ";");
		return code;
	}
}
