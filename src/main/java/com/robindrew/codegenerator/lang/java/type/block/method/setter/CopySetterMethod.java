package com.robindrew.codegenerator.lang.java.type.block.method.setter;

import java.util.List;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class CopySetterMethod extends JavaMethod {

	private final IJavaName fieldName;

	public CopySetterMethod(IJavaNamedType setField, IJavaType returnType, IJavaType copyType, List<IJavaNamedType> fields, IJavaCodeLines code) {
		super("set" + setField.getName().toUpper(), returnType);
		this.fieldName = setField.getName();
		getParameters().add(setField);

		// Comment
		setStandardComment();

		// Content
		setCopyContents(setField, copyType, fields, code);
	}

	public CopySetterMethod(IJavaNamedType setField, IJavaType returnType, IJavaType copyType, List<IJavaNamedType> fields) {
		super("set" + setField.getName().toUpper(), returnType);
		this.fieldName = setField.getName();
		getParameters().add(setField);

		// Comment
		setStandardComment();
	}

	private void setCopyContents(IJavaNamedType setField, IJavaType copyType, List<IJavaNamedType> fields, IJavaCodeLines code) {
		boolean comma = false;
		StringBuilder args = new StringBuilder();
		for (IJavaNamedType field : fields) {
			if (comma) {
				args.append(", ");
			}
			comma = true;
			String lower = field.getName().toLower().get();
			args.append(lower);
		}
		code.line("return new " + copyType.getSimpleName(true) + "(" + args + ");");
		setContents(code);
	}

	public IJavaName getFieldName() {
		return fieldName;
	}

	public CopySetterMethod setStandardComment() {
		getComment().line("Setter for the " + fieldName + " field, return a copy with the field set.");
		getComment().line("@param " + fieldName + " the " + fieldName + " value to set.");
		getComment().line("@return a copy of this object.");
		return this;
	}

}
