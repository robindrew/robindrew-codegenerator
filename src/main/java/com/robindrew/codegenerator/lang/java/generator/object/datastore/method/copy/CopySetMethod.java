package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.copy;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;

public class CopySetMethod extends DelegateMethod {

	private final JavaModelDataStore dataStore;

	public CopySetMethod(IJavaMethod method, JavaModelDataStore dataStore) {
		super(method);
		this.dataStore = dataStore;
	}

	public IJavaCodeLines copyParameter(IJavaCodeLines code) {
		String name = dataStore.getElementBean().getType().getSimpleName(true);
		code.line("if (copyOnWrite) {");
		code.line(1, "element = new " + name + "(element);");
		code.line("}");
		return code;
	}

	public CopySetMethod setDelegateContents() {
		IJavaCodeLines contents = new JavaCodeLines();
		copyParameter(contents);
		setDelegateContents(contents);
		setContents(contents);
		return this;
	}
}
