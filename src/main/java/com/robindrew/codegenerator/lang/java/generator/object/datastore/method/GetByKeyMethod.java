package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStoreKey;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetByKeyMethod extends JavaMethod {

	private final JavaModelBean bean;
	private final JavaModelDataStore dataStore;

	public GetByKeyMethod(JavaModelDataStore dataStore, JavaModelBean bean, JavaModelDataStoreKey key) {
		super("get" + toUpper(bean.getName()) + "By" + toUpper(key.getBean().getName()), key.isUnique() ? bean.getInterfaceType() : dataStore.asList(bean.getInterfaceType()));
		for (JavaModelBeanField field : key.getBean().getFieldList()) {
			getParameters().add(field.getName(), field.getType());
		}
		this.bean = bean;
		this.dataStore = dataStore;
	}

	public JavaModelBean getBean() {
		return bean;
	}

	public JavaModelDataStore getDataStore() {
		return dataStore;
	}

}
