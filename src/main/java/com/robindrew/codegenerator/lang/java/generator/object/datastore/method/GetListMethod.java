package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetListMethod extends JavaMethod {

	public GetListMethod(JavaModelDataStore dataStore, JavaModelBean bean) {
		super("get" + toUpper(bean.getName()) + "List", dataStore.asList(bean.getInterfaceType()));
	}
}
