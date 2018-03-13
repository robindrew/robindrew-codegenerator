package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class RemoveAllMethod extends JavaMethod {

	private final JavaModelDataStore dataStore;

	public RemoveAllMethod(JavaModelDataStore dataStore) {
		super("removeAll");
		this.dataStore = dataStore;
		getParameters().add("elements", dataStore.asCollection(true));
	}

	public RemoveAllMethod setDefaultContents() {
		String elementType = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + elementType + " element : elements) {");
		code.line(1, "remove(element);");
		code.line("}");
		setContents(code);
		return this;
	}

}
