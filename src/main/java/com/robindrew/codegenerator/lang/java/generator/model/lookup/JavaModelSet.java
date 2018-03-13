package com.robindrew.codegenerator.lang.java.generator.model.lookup;

import java.util.ArrayList;
import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class JavaModelSet implements IJavaModelSet {

	private final List<JavaModel> modelList;

	public JavaModelSet(List<JavaModel> modelList) {
		if (modelList.isEmpty()) {
			throw new IllegalArgumentException("modelList is empty");
		}
		this.modelList = modelList;
	}

	@Override
	public List<JavaModel> getModelList() {
		return modelList;
	}

	@Override
	public List<JavaModelBean> getBeanList() {
		List<JavaModelBean> list = new ArrayList<JavaModelBean>();
		for (JavaModel model : modelList) {
			list.addAll(model.getBeanList());
		}
		return list;
	}

	@Override
	public List<JavaModelEnum> getEnumList() {
		List<JavaModelEnum> list = new ArrayList<JavaModelEnum>();
		for (JavaModel model : modelList) {
			list.addAll(model.getEnumList());
		}
		return list;
	}

	@Override
	public List<JavaModelResultSetParser> getResultSetParserList() {
		List<JavaModelResultSetParser> list = new ArrayList<JavaModelResultSetParser>();
		for (JavaModel model : modelList) {
			list.addAll(model.getResultSetParserList());
		}
		return list;
	}

	@Override
	public List<JavaModelValidator> getValidatorList() {
		List<JavaModelValidator> list = new ArrayList<JavaModelValidator>();
		for (JavaModel model : modelList) {
			list.addAll(model.getValidatorList());
		}
		return list;
	}

	@Override
	public List<JavaModelDataSerializer> getSerializerList() {
		List<JavaModelDataSerializer> list = new ArrayList<JavaModelDataSerializer>();
		for (JavaModel model : modelList) {
			list.addAll(model.getDataSerializerList());
		}
		return list;
	}

	@Override
	public List<JavaModelDataStore> getDataStoreList() {
		List<JavaModelDataStore> list = new ArrayList<JavaModelDataStore>();
		for (JavaModel model : modelList) {
			list.addAll(model.getDataStoreList());
		}
		return list;
	}

	@Override
	public List<JavaModelInterface> getInterfaceList() {
		List<JavaModelInterface> list = new ArrayList<JavaModelInterface>();
		for (JavaModel model : modelList) {
			list.addAll(model.getInterfaceList());
		}
		return list;
	}

	@Override
	public JavaModelValidator getValidator(String name, boolean optional) {
		for (JavaModelValidator validator : getValidatorList()) {
			if (validator.getName().equals(name)) {
				return validator;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + name + "'");
		}
		return null;
	}

	@Override
	public JavaModelInterface getInterface(String name, boolean optional) {
		for (JavaModelInterface iinterface : getInterfaceList()) {
			if (iinterface.getName().equals(name)) {
				return iinterface;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + name + "'");
		}
		return null;
	}

	@Override
	public JavaModelInterface getInterface(IJavaType type, boolean optional) {
		for (JavaModelInterface iinterface : getInterfaceList()) {
			if (iinterface.getType().equals(type)) {
				return iinterface;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + type + "'");
		}
		return null;
	}

	@Override
	public JavaModelBean getBean(String name, boolean optional) {
		for (JavaModelBean bean : getBeanList()) {
			if (bean.getName().equals(name)) {
				return bean;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + name + "'");
		}
		return null;
	}

	@Override
	public JavaModelBean getBean(IJavaType type, boolean optional) {
		for (JavaModelBean bean : getBeanList()) {
			if (bean.getType().equals(type)) {
				return bean;
			}
			if (bean.getInterfaceType().equals(type)) {
				return bean;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + type + "'");
		}
		return null;
	}

	@Override
	public JavaModelResultSetParser getResultSetParser(String name, boolean optional) {
		for (JavaModelResultSetParser parser : getResultSetParserList()) {
			if (parser.getName().equals(name)) {
				return parser;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("parser not found: '" + name + "'");
		}
		return null;
	}

	@Override
	public JavaModelEnum getEnum(String name, boolean optional) {
		for (JavaModelEnum bean : getEnumList()) {
			if (bean.getName().equals(name)) {
				return bean;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + name + "'");
		}
		return null;
	}

	@Override
	public JavaModelEnum getEnum(IJavaType type, boolean optional) {
		for (JavaModelEnum eenum : getEnumList()) {
			if (eenum.getType().equals(type)) {
				return eenum;
			}
		}
		if (!optional) {
			throw new IllegalArgumentException("bean not found: '" + type + "'");
		}
		return null;
	}

}
