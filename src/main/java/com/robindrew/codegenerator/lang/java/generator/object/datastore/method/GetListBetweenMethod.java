package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetListBetweenMethod extends JavaMethod {

	private final IJavaType type;

	public GetListBetweenMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter) {
		super("get" + dataStore.getElementBean().getName() + "ListBetween" + toUpper(parameter.getName()) + "s", dataStore.asList());
		type = parameter.getType().toObjectType();
		getParameters().add("from", type);
		getParameters().add("to", type);
		getReferences().add(List.class);
	}
}
