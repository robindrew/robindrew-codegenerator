package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import java.util.ArrayList;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.RemoveListByMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;

public class MapRemoveListByMethod extends RemoveListByMethod {

	private final JavaModelDataStore dataStore;
	private final JavaModelBeanField parameter;

	public MapRemoveListByMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter, JavaModelBean bean, boolean identity) {
		super(dataStore, parameter, bean, identity);
		this.dataStore = dataStore;
		this.parameter = parameter;
		setOverride();

		// Imports
		if (!identity) {
			getReferences().add(ArrayList.class);
		}

		// Contents
		setContents(removeListContents(bean));
	}

	private IJavaCodeBlock removeListContents(JavaModelBean bean) {
		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);

		IJavaCodeLines code = new JavaCodeLines();
		code.line("for (" + type + " element : new ArrayList<>(map.values())) {");
		code.line(1, "if (" + new Equals().method1(parameter, "element").field2(parameter) + ") {");
		code.line(2, "remove(element);");
		code.line(1, "}");
		code.line("}");
		return code;
	}
}
