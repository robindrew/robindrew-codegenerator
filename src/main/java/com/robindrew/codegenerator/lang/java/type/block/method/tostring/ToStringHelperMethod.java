package com.robindrew.codegenerator.lang.java.type.block.method.tostring;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class ToStringHelperMethod extends ToStringMethod {

	public ToStringHelperMethod(IJavaNamedTypeSet fields) {
		super(fields);
	}

	protected IJavaCodeBlock createContents() {
		JavaCodeLines code = new JavaCodeLines();
		code.line("ToStringHelper helper = Objects.toStringHelper(getClass());");
		for (IJavaNamedType field : getFields()) {
			IJavaName name = field.getName().toUpper();
			code.line("helper.add(\"" + name + "\", get" + name.toUpper() + "());");
		}
		code.line("return helper.toString();");
		return code;
	}
}
