package com.robindrew.codegenerator.lang.java.generator.object.sql.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toLower;

import java.sql.ResultSet;

import com.robindrew.codegenerator.api.sql.parser.ResultSetTypes;
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

		getParameters().add("set", ResultSet.class);
		getReferences().add(ResultSetTypes.class);

		setOverride();
		setContents(createContents());
	}

	private IJavaCodeBlock createContents() {
		String returnName = getReturnType().getSimpleName(true);
		String beanName = bean.getType().getSimpleName(true);

		StringBuilder names = new StringBuilder();
		JavaCodeLines code = new JavaCodeLines();
		int index = 1;
		for (JavaModelBeanField field : bean.getFieldList()) {
			IJavaType type = field.getType();
			getReferences().add(type);

			String fieldName = toLower(field.getName());

			append(code, field, type, fieldName, index);

			if (names.length() > 0) {
				names.append(", ");
			}
			names.append(fieldName);
			index++;
		}

		code.line(returnName + " row = new " + beanName + "(" + names + ");");
		code.line("return row;");
		return code;
	}

	private void append(JavaCodeLines code, JavaModelBeanField field, IJavaType type, String fieldName, int index) {
		String typeName = field.getType().getSimpleName(true);
		String typeUpper = JavaName.toUpper(typeName);
		if (type.isEnum()) {
			code.line(typeName + " " + fieldName + " = ResultSetTypes.parseEnum(set, " + index + ", \"" + fieldName + "\", " + typeName + ".class, true);");
			return;
		}
		if (type.isArray()) {
			String component = type.getComponentType().getSimpleName(true);
			component = JavaName.toUpper(component);
			code.line(typeName + " " + fieldName + " = ResultSetTypes.parse" + component + "Array(set, " + index + ", \"" + fieldName + "\", true);");
			return;
		}
		if (type.isPrimitive()) {
			code.line(typeName + " " + fieldName + " = ResultSetTypes.parse" + typeUpper + "(set, " + index + ", \"" + fieldName + "\");");
			return;
		}
		code.line(typeName + " " + fieldName + " = ResultSetTypes.parse" + typeUpper + "(set, " + index + ", \"" + fieldName + "\", true);");
	}
}
