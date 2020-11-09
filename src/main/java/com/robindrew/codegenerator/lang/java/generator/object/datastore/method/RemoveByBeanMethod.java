package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class RemoveByBeanMethod extends JavaMethod {

	private final JavaModelBean bean;
	private final JavaModelDataStore dataStore;

	public RemoveByBeanMethod(JavaModelDataStore dataStore) {
		this(dataStore, dataStore.getKeyBean());
	}

	public RemoveByBeanMethod(JavaModelDataStore dataStore, JavaModelBean bean) {
		super("removeBy" + bean.getType().getSimpleName(false));
		this.bean = bean;
		this.dataStore = dataStore;
		getParameters().add("key", bean.getInterfaceType());
	}

	public JavaModelBean getBean() {
		return bean;
	}

	public JavaModelDataStore getDataStore() {
		return dataStore;
	}

}
