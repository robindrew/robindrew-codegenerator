package com.robindrew.codegenerator.lang.java.type.block.method.build;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class BuildMethod extends JavaMethod {

	private final JavaModelBean bean;

	public BuildMethod(JavaModelBean bean) {
		this(bean, bean.getInterfaceType());
	}

	public BuildMethod(JavaModelBean bean, IJavaType type) {
		super("build", type);
		this.bean = bean;

		// Comment
		setStandardComment();

		// Contents
		setContents();
	}

	private void setContents() {

		StringBuilder line = new StringBuilder();
		line.append("return new ").append(bean.getName()).append("(");
		boolean comma = false;
		for (IJavaNamedType field : bean.getFields()) {
			if (comma) {
				line.append(", ");
			}
			comma = true;
			line.append(field.getName());
		}
		line.append(");");

		// Contents
		JavaCodeLines block = new JavaCodeLines();
		block.line(line.toString());
		setContents(block);
	}

	public void setStandardComment() {
		getComment().line("Build a new " + bean.getName() + ".");
		getComment().line("@return a new " + bean.getName() + ".");
	}

}
