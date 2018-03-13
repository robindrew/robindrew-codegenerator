package com.robindrew.codegenerator.lang.java.type.block.method.equals;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class EqualsMethod extends JavaMethod {

	private final IJavaType type;
	private final IJavaNamedTypeSet fields;

	public EqualsMethod(IJavaType type, IJavaNamedTypeSet fields) {
		super("equals", boolean.class);
		getParameters().add("object", Object.class);
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
		if (!fields.isEmpty()) {
			getReferences().add(EqualsBuilder.class);
		}
	}

	private IJavaCodeBlock createContents() {
		JavaCodeLines code = new JavaCodeLines();

		// Identity
		code.line("// Identity check");
		code.line("if (object == this) {");
		code.line(1, "return true;");
		code.line("}");
		code.emptyLine();

		// Null
		code.line("// Null check");
		code.line("if (object == null) {");
		code.line(1, "return false;");
		code.line("}");
		code.emptyLine();

		// Type
		code.line("// Compare types");
		code.line("if (!this.getClass().equals(object.getClass())) {");
		code.line(1, "return false;");
		code.line("}");
		code.emptyLine();

		// No fields to compare?
		if (fields.isEmpty()) {
			code.line("// No fields to compare ...");
			code.line("return true;");
			return code;
		}

		// Equals
		code.line("// Compare fields");
		IJavaName typeName = new JavaName(type.getSimpleName());
		code.line(typeName + " that = (" + typeName + ") object;");
		code.line("EqualsBuilder builder = new EqualsBuilder();");
		for (IJavaNamedType field : fields) {
			IJavaName name = field.getName().toUpper();
			code.line("builder.append(this.get" + name + "(), that.get" + name + "());");
		}
		code.line("return builder.isEquals();");

		return code;
	}
}
