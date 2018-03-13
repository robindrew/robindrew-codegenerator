package com.robindrew.codegenerator.lang.java.generator.object.datastore.method;

import static com.robindrew.codegenerator.lang.java.type.name.JavaName.toUpper;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;

public class GetListByMethod extends JavaMethod {

	private static String getName(JavaModelBeanField parameter, JavaModelBean bean, boolean unique) {
		StringBuilder name = new StringBuilder();
		name.append("get").append(bean.getName());
		name.append(unique ? "By" : "ListBy");
		name.append(toUpper(parameter.getName()));
		return name.toString();
	}

	private static IJavaType getReturnType(JavaModelDataStore dataStore, JavaModelBean bean, boolean unique) {
		IJavaType type = bean.getInterfaceType();
		if (!unique) {
			type = dataStore.asList(type);
		}
		return type;
	}

	public GetListByMethod(JavaModelDataStore dataStore, JavaModelBeanField parameter, JavaModelBean bean, boolean unique) {
		super(getName(parameter, bean, unique), getReturnType(dataStore, bean, unique));
		getParameters().add(parameter.getName(), parameter.getType());

		if (!unique) {
			getReferences().add(List.class);
		}
	}
}
