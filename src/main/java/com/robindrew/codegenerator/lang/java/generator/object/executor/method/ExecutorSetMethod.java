package com.robindrew.codegenerator.lang.java.generator.object.executor.method;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class ExecutorSetMethod extends JavaMethod {

	public ExecutorSetMethod(JavaModelBean bean, boolean interfaceMethod) {
		super(bean.getName(), bean.getReturnType());
		setInterface(interfaceMethod);
		if (!interfaceMethod) {
			setOverride();
		}
		for (JavaModelBeanField field : bean.getFieldList()) {
			getParameters().add(field.getName(), field.getType());
		}
		JavaCodeLines code = new JavaCodeLines();
		code.line(newBeanLine(bean));
		code.line("return getLocator().locateExecutor(bean).executeBean(bean);");
		setContents(code);
	}

	private String newBeanLine(JavaModelBean bean) {
		StringBuilder code = new StringBuilder();
		code.append(bean.getName()).append(" bean = new ").append(bean.getName());
		code.append("(");
		boolean comma = false;
		for (JavaModelBeanField field : bean.getFieldList()) {
			if (comma) {
				code.append(", ");
			}
			comma = true;
			code.append(field.getName());
		}
		code.append(");");
		return code.toString();
	}

}
