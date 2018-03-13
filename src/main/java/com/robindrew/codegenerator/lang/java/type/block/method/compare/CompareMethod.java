package com.robindrew.codegenerator.lang.java.type.block.method.compare;

import static java.util.Collections.reverse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class CompareMethod extends JavaMethod {

	private final IJavaType type;
	private final List<IJavaName> fields;
	private final boolean reverseFields;
	private final boolean swapFields;

	public CompareMethod(IJavaType type, List<IJavaName> fields, boolean reverse, boolean swap) {
		super("compare", int.class);
		getParameters().add("compare1", type);
		getParameters().add("compare2", type);
		if (fields == null) {
			throw new NullPointerException("fields");
		}
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
		this.fields = fields;
		this.reverseFields = reverse;
		this.swapFields = swap;

		setOverride();
		setContents(createContents());
		getReferences().add(CompareToBuilder.class);
	}

	private IJavaCodeBlock createContents() {
		JavaCodeLines code = new JavaCodeLines();

		// Compare
		code.line("CompareToBuilder builder = new CompareToBuilder();");
		createContents(code);
		code.line("return builder.toComparison();");

		return code;
	}

	private void createContents(JavaCodeLines code) {
		List<IJavaName> reverseList = new ArrayList<IJavaName>(fields);
		reverse(reverseList);

		if (reverseFields) {
			code.line("if (reverse) {");
			createContents(code, 1, reverseList);
			code.line("} else {");
			createContents(code, 1, fields);
			code.line("}");
		} else {
			createContents(code, 0, fields);
		}
	}

	private void createContents(JavaCodeLines code, int indent, List<IJavaName> fields) {
		if (swapFields) {
			code.line(indent, "if (swap) {");
			createContents(code, indent + 1, fields, true);
			code.line(indent, "} else {");
			createContents(code, indent + 1, fields, false);
			code.line(indent, "}");
		} else {
			createContents(code, indent, fields, false);
		}
	}

	private void createContents(JavaCodeLines code, int indent, List<IJavaName> fields, boolean swap) {
		for (IJavaName field : fields) {
			IJavaName name = field.toUpper();
			if (swap) {
				code.line(indent, "builder.append(compare2.get" + name + "(), compare1.get" + name + "());");
			} else {
				code.line(indent, "builder.append(compare1.get" + name + "(), compare2.get" + name + "());");
			}
		}
	}

	public IJavaType getType() {
		return type;
	}
}
