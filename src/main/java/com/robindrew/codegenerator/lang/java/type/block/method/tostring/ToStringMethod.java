package com.robindrew.codegenerator.lang.java.type.block.method.tostring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;

public abstract class ToStringMethod extends JavaMethod {

	private final IJavaNamedTypeSet fields;

	public ToStringMethod(IJavaNamedTypeSet fields) {
		super("toString", String.class);
		if (fields == null) {
			throw new NullPointerException("fields");
		}
		this.fields = fields;

		setOverride();
		setContents(createContents());
		getReferences().add(ToStringBuilder.class);
		getReferences().add(ToStringStyle.class);
	}

	public IJavaNamedTypeSet getFields() {
		return fields;
	}

	protected abstract IJavaCodeBlock createContents();

}
