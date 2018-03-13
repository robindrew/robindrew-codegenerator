package com.robindrew.codegenerator.lang.java.type.block.method.compare;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class CompareToMethod extends JavaMethod {

	private final IJavaType type;
	private final IJavaNamedTypeSet fields;

	public CompareToMethod(IJavaType type, IJavaNamedTypeSet fields) {
		super("compareTo", int.class);
		getParameters().add("that", type);
		if (fields == null) {
			throw new NullPointerException("fields");
		}
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
		this.fields = fields;

		setOverride();
		setContents(createContents());
		getReferences().add(CompareToBuilder.class);
	}

	public IJavaType getType() {
		return type;
	}

	public IJavaNamedTypeSet getFields() {
		return fields;
	}

	private IJavaCodeBlock createContents() {
		JavaCodeLines code = new JavaCodeLines();

		// Compare
		code.line("CompareToBuilder builder = new CompareToBuilder();");
		for (IJavaNamedType field : fields) {
			IJavaName name = field.getName().toUpper();
			code.line("builder.append(this.get" + name + "(), that.get" + name + "());");
		}
		code.line("return builder.toComparison();");

		return code;
	}
}
