package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetByBeanMethod extends JavaMethod {

	private final JavaModelBean bean;

	public GetByBeanMethod(JavaModelDataStore dataStore) {
		this(dataStore, dataStore.getKeyBean());
	}

	public GetByBeanMethod(JavaModelDataStore dataStore, JavaModelBean bean) {
		super("get", dataStore.getElementBean().getInterfaceType());
		this.bean = bean;
		getParameters().add("key", bean.getInterfaceType());
	}

	public JavaModelBean getBean() {
		return bean;
	}

}
