package com.robindrew.codegenerator.lang.java.generator.model.datastore;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStoreKey;

public class JavaModelDataStoreKey extends JavaModelTyped<ModelDataStoreKey> {

	private final JavaModelBean bean;

	public JavaModelDataStoreKey(ModelDataStoreKey model, JavaModelBean bean) {
		super(model);
		this.bean = bean;
	}

	public boolean isUnique() {
		return get().isUnique();
	}
	
	public JavaModelBean getBean() {
		return bean;
	}

}
