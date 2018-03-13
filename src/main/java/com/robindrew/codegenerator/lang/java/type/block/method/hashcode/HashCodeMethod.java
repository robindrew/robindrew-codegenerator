package com.robindrew.codegenerator.lang.java.type.block.method.hashcode;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class HashCodeMethod extends JavaMethod {

	private final IJavaNamedTypeSet fields;

	public HashCodeMethod(IJavaNamedTypeSet fields) {
		super("hashCode", int.class);
		if (fields == null) {
			throw new NullPointerException("fields");
		}
		this.fields = fields;

		setOverride();
		setContents(createContents());
		getReferences().add(HashCodeBuilder.class);
	}

	private IJavaCodeBlock createContents() {
		JavaCodeLines code = new JavaCodeLines();
		code.line("HashCodeBuilder builder = new HashCodeBuilder();");
		for (IJavaNamedType field : fields) {
			IJavaName name = field.getName().toUpper();
			code.line("builder.append(get" + name + "());");
		}
		code.line("return builder.toHashCode();");
		return code;
	}
}
