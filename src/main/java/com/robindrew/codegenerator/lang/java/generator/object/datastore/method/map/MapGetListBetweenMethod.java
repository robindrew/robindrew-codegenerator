package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.map;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.object.datastore.method.GetListBetweenMethod;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class MapGetListBetweenMethod extends GetListBetweenMethod {

	private final JavaModelDataStore dataStore;
	private final JavaModelBeanField parameter;

	public MapGetListBetweenMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter) {
		super(dataStore, parameter);
		this.dataStore = dataStore;
		this.parameter = parameter;
		setOverride();

		setContents(getListContents());
	}

	private IJavaCodeBlock getListContents() {
		String type = dataStore.getElementBean().getInterfaceType().getSimpleName(true);
		boolean primitive = parameter.getType().isPrimitive();

		IJavaCodeLines code = new JavaCodeLines();
		code.line("List<" + type + "> list = new ArrayList<" + type + ">();");
		code.line("for (" + type + " dataStore : map.values()) {");
		code.line(1, "if (from != null) {");
		code.line(2, "if (dataStore." + getCompare(primitive, "<") + " from ){");
		code.line(3, "continue;");
		code.line(2, "}");
		code.line(1, "}");
		code.line(1, "if (to != null) {");
		code.line(2, "if (dataStore." + getCompare(primitive, ">") + " to ){");
		code.line(3, "continue;");
		code.line(2, "}");
		code.line(1, "}");
		code.line(1, "list.add(dataStore);");
		code.line("}");
		code.line("return list;");
		return code;
	}

	private String getCompare(boolean primitive, String operator) {
		StringBuilder code = new StringBuilder();
		code.append("get");
		code.append(JavaName.toUpper(parameter.getName())).append("()");
		if (!primitive) {
			throw new IllegalStateException("not supported");
		}
		code.append(" ").append(operator).append(" ");
		return code.toString();
	}
}
