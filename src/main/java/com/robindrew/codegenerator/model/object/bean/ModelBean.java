package com.robindrew.codegenerator.model.object.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;
import com.robindrew.codegenerator.model.object.iinterface.ModelExtends;

public class ModelBean extends ModelObject {

	@Attribute(required = false)
	private boolean immutable = false;
	@Attribute(required = false)
	private Long version = null;
	@Attribute(required = false)
	private boolean compare = true;
	@Attribute(required = false)
	private String returnType = null;
	@ElementList(entry = "Field", inline = true, required = false)
	private List<ModelBeanField> fieldList = new ArrayList<ModelBeanField>();
	@ElementList(entry = "Extends", inline = true, required = false)
	private List<ModelExtends> extendsList = new ArrayList<ModelExtends>();
	@ElementList(entry = "Constructor", inline = true, required = false)
	private List<ModelBeanConstructor> constructorList = new ArrayList<ModelBeanConstructor>();
	@ElementList(entry = "Method", inline = true, required = false)
	private List<ModelBeanMethod> methodList = new ArrayList<ModelBeanMethod>();

	public List<ModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<ModelBeanConstructor> getConstructorList() {
		return constructorList;
	}

	public List<ModelBeanMethod> getMethodList() {
		return methodList;
	}

	public boolean isImmutable() {
		return immutable;
	}

	public boolean isCompare() {
		return compare;
	}

	public List<ModelBeanField> getFieldList() {
		return fieldList;
	}

	public Long getSerialVersionUid() {
		return version;
	}

	public String getReturnType() {
		return returnType;
	}

}
