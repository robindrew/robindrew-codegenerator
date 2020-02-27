package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetByRowMethod extends JavaMethod {

	private final JavaModelDataStore dataStore;
	private final JavaModelBean bean;
	private final JavaModelBean row;

	public GetByRowMethod(JavaModelDataStore dataStore, JavaModelBean bean, JavaModelBean row) {
		super("getBy" + toUpper(row.getName()), dataStore.asList(bean.getInterfaceType()));
		for (JavaModelBeanField field : row.getFieldList()) {
			getParameters().add(field.getName(), field.getType());
		}
		this.dataStore = dataStore;
		this.bean = bean;
		this.row = row;
	}

	public JavaModelBean getBean() {
		return bean;
	}

	public JavaModelBean getRow() {
		return row;
	}

	public JavaModelDataStore getDataStore() {
		return dataStore;
	}

}
