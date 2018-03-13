package com.robindrew.codegenerator.lang.java.type.block.method.tostring;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class ToStringBuilderMethod extends ToStringMethod {

	public ToStringBuilderMethod(IJavaNamedTypeSet fields) {
		super(fields);
	}

	protected IJavaCodeBlock createContents() {
		JavaCodeLines code = new JavaCodeLines();
		code.line("ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);");
		for (IJavaNamedType field : getFields()) {
			IJavaName name = field.getName().toUpper();
			code.line("builder.append(get" + name + "());");
		}
		code.line("return builder.toString();");
		return code;
	}
}
