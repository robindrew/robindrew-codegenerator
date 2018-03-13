package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetMethod extends JavaMethod {

	private final List<JavaModelBeanField> fields;

	public GetMethod(JavaModelDataStore dataStore) {
		this(dataStore, dataStore.getKeyBean().getFieldList());
	}

	public GetMethod(JavaModelDataStore dataStore, List<JavaModelBeanField> fields) {
		super("get", dataStore.getElementBean().getInterfaceType());
		this.fields = fields;
		for (JavaModelBeanField field : fields) {
			getParameters().add(field.getName(), field.getType());
		}
	}

	public List<JavaModelBeanField> getFields() {
		return fields;
	}

}
