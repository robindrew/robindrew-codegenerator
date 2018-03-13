package com.robindrew.codegenerator.lang.java.type.block.method.setter;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;

public class SetFieldsBlock {

	private final IJavaNamedTypeSet fields;
	private final boolean direct;

	public SetFieldsBlock(IJavaNamedTypeSet fields, boolean direct) {
		if (fields == null) {
			throw new NullPointerException("fields");
		}
		this.fields = fields;
		this.direct = direct;
	}

	public SetFieldsBlock(IJavaNamedTypeSet fields) {
		this(fields, false);
	}

	public void populate(IJavaMethod method) {
		IJavaCodeLines code = new JavaCodeLines();
		populate(code);
		method.getParameters().addAll(fields);
		method.setContents(code);
	}

	public void populate(IJavaCodeLines code) {
		for (IJavaNamedType field : fields) {

			// Direct?
			if (direct) {
				code.line("this." + field.getName() + " = " + field.getName() + ";");
			} else {
				code.line("set" + field.getName().toUpper() + "(" + field.getName() + ");");
			}
		}
	}

	public void populate(IJavaMethod method, IJavaNamedType namedType) {
		String name = namedType.getName().toLower().get();
		method.getParameters().add(name, namedType.getType());

		IJavaCodeLines assignments = new JavaCodeLines();
		for (IJavaNamedType field : fields) {

			// Direct?
			if (direct) {
				assignments.line("this." + field.getName() + " = " + name + ".get" + field.getName().toUpper() + "();");
			} else {
				assignments.line("set" + field.getName().toUpper() + "(" + name + ".get" + field.getName().toUpper() + "());");
			}
		}
		method.setContents(assignments);
	}

	public void populate(IJavaMethod constructor, IJavaType type) {
		IJavaNamedType namedType = new JavaNamedType(type.getSimpleName(), type);
		populate(constructor, namedType);
	}

}
