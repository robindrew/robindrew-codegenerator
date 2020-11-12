package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;

public class CountColumnMethod extends JavaMethod {

	public CountColumnMethod(JavaModelBeanField field) {
		super("get" + JavaName.toUpper(field.getName()) + "Count", int.class);
		getParameters().add(field.toNamedType());
	}

}
