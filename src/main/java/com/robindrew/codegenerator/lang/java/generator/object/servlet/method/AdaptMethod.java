package com.robindrew.codegenerator.lang.java.generator.object.servlet.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import javax.servlet.ServletRequest;

import com.robindrew.codegenerator.api.servlet.ServletRequestTypes;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class AdaptMethod extends JavaMethod {

	private final JavaModelBean bean;

	public AdaptMethod(JavaModelBean bean) {
		super("adapt", bean.getInterfaceType());
		this.bean = bean;

		getParameters().add("request", ServletRequest.class);
		getReferences().add(ServletRequestTypes.class);

		setOverride();
		setContents(createContents());
	}

	private IJavaCodeBlock createContents() {
		String returnName = getReturnType().getSimpleName(true);
		String beanName = bean.getType().getSimpleName(true);

		StringBuilder names = new StringBuilder();
		JavaCodeLines code = new JavaCodeLines();
		for (JavaModelBeanField field : bean.getFieldList()) {
			IJavaType type = field.getType();
			getReferences().add(type);

			String fieldName = toLower(field.getName());

			append(code, field, type, fieldName);

			if (names.length() > 0) {
				names.append(", ");
			}
			names.append(fieldName);
		}

		code.line(returnName + " row = new " + beanName + "(" + names + ");");
		code.line("return row;");
		return code;
	}

	private void append(JavaCodeLines code, JavaModelBeanField field, IJavaType type, String fieldName) {
		String typeName = field.getType().getSimpleName(true);
		String typeUpper = JavaName.toUpper(typeName);
		if (type.isEnum()) {
			code.line(typeName + " " + fieldName + " = ServletRequestTypes.parseEnum(request, \"" + fieldName + "\", " + typeName + ".class, true);");
			return;
		}
		if (type.isArray()) {
			String component = type.getComponentType().getSimpleName(true);
			component = JavaName.toUpper(component);
			code.line(typeName + " " + fieldName + " = ServletRequestTypes.parse" + component + "Array(request, \"" + fieldName + "\", true);");
			return;
		}
		if (type.isPrimitive()) {
			code.line(typeName + " " + fieldName + " = ServletRequestTypes.parse" + typeUpper + "(request, \"" + fieldName + "\");");
			return;
		}
		code.line(typeName + " " + fieldName + " = ServletRequestTypes.parse" + typeUpper + "(request, \"" + fieldName + "\", true);");
	}
}
