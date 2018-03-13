package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;

public class CopyGetMethod extends DelegateMethod {

	private final JavaModelDataStore dataStore;

	public CopyGetMethod(IJavaMethod method, JavaModelDataStore dataStore) {
		super(method);
		this.dataStore = dataStore;

		// Contents
		setContents(getMethodContents());
	}

	private IJavaCodeBlock getMethodContents() {
		String returnType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);
		String beanType = dataStore.getElementBean().getType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line(returnType + " returnValue = " + getDelegateCall() + ";");
		code.line("if (copyOnRead) {");
		code.line(1, "returnValue = new " + beanType + "(returnValue);");
		code.line("}");
		code.line("return returnValue;");
		return code;
	}
}
