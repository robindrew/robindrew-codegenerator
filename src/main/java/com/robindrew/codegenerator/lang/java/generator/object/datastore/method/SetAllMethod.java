package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class SetAllMethod extends JavaMethod {

	private final JavaModelDataStore dataStore;

	public SetAllMethod(JavaModelDataStore dataStore) {
		super("setAll");
		this.dataStore = dataStore;
		getParameters().add("elements", dataStore.asCollection(true));
	}

	public SetAllMethod setDefaultContents() {
		String elementType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + elementType + " element : elements) {");
		code.line(1, "set(element);");
		code.line("}");
		setContents(code);
		return this;
	}

}
