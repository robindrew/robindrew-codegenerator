package com.robindrew.codegenerator.lang.java.generator.model.datastore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBeanField;
import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.generator.model.common.JavaModelMethod;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeContainer;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.resolver.IJavaTypeResolver;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStore;

public class JavaModelDataStore {

	private final ModelDataStore model;
	private final List<JavaModelExtends> extendsList;
	private final List<JavaModelMethod> methodList;
	private volatile IJavaTypeResolver resolver;

	private volatile JavaModelBean elementBean;
	private volatile JavaModelBean keyBean;
	private volatile List<JavaModelBean> rowBeans;
	private volatile List<JavaModelDataStoreKey> keyBeans;

	private volatile IJavaType interfaceType;
	private volatile IJavaType readOnlyInterfaceType;

	public JavaModelDataStore(ModelDataStore model, List<JavaModelExtends> extendsList, List<JavaModelMethod> methodList) {
		this.model = model;
		this.extendsList = extendsList;
		this.methodList = methodList;
	}

	public String getName() {
		return get().getName();
	}

	public ModelDataStore get() {
		return model;
	}

	public ModelDataStore getModel() {
		return model;
	}

	public IJavaTypeResolver getResolver() {
		return resolver;
	}

	public JavaModelBean getElementBean() {
		return elementBean;
	}

	public List<JavaModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<JavaModelMethod> getMethodList() {
		return methodList;
	}

	public JavaModelBean getKeyBean() {
		return keyBean;
	}

	public List<JavaModelBean> getRowBeans() {
		return rowBeans;
	}

	public List<JavaModelDataStoreKey> getKeyBeans() {
		return keyBeans;
	}

	public IJavaType getInterfaceType() {
		return interfaceType;
	}

	public IJavaType getReadOnlyInterfaceType() {
		return readOnlyInterfaceType;
	}

	public void setResolver(IJavaTypeResolver resolver) {
		this.resolver = resolver;
	}

	public void setBeans(JavaModelBean elementBean, JavaModelBean keyBean, List<JavaModelBean> rowBeans, List<JavaModelDataStoreKey> keyBeans) {
		this.elementBean = elementBean;
		this.keyBean = keyBean;
		this.rowBeans = rowBeans;
		this.keyBeans = keyBeans;
	}

	public void setInterfaceTypes(IJavaType interfaceType, IJavaType readOnlyInterfaceType) {
		this.interfaceType = interfaceType;
		this.readOnlyInterfaceType = readOnlyInterfaceType;
	}

	public IJavaType asList() {
		return asList(getElementBean().getInterfaceType());
	}

	public IJavaType asArrayList() {
		return asArrayList(getElementBean().getInterfaceType());
	}

	public IJavaType asList(IJavaType type) {
		IJavaType listType = resolver.getType(List.class);
		return new JavaTypeWithGenerics(listType, type);
	}

	public IJavaType asArrayList(IJavaType type) {
		IJavaType listType = resolver.getType(ArrayList.class);
		return new JavaTypeWithGenerics(listType, type);
	}

	public IJavaType asCollection(boolean wildcard) {
		IJavaType collectionType = resolver.getType(Collection.class);
		IJavaType type = getElementBean().getInterfaceType();
		if (wildcard) {
			type = new JavaTypeContainer(type, true, false);
		}
		return new JavaTypeWithGenerics(collectionType, type);
	}

	public List<JavaModelBeanField> getUniqueFieldList() {
		return getElementBean().getUniqueFieldList();
	}

	public boolean isAutoIncrement() {
		return getElementBean().getAutoIncrementField() != null;
	}

}
