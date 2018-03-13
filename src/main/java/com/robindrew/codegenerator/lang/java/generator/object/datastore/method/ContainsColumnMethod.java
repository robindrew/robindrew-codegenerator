package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class ContainsColumnMethod extends JavaMethod {

	public ContainsColumnMethod(JavaModelBeanField field) {
		super("contains" + JavaName.toUpper(field.getName()), boolean.class);
		getParameters().add(field.toNamedType());
	}

}
