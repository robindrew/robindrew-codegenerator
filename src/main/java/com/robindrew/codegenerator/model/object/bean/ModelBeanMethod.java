package com.robindrew.codegenerator.model.object.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelBeanMethod extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute(required = false)
	private String returnType = "void";
	@Attribute(required = false)
	private String returnValue = null;
	@ElementList(entry = "Parameter", inline = true, required = false)
	private List<ModelBeanParameter> parameterList = new ArrayList<ModelBeanParameter>();

	public String getName() {
		return name;
	}

	public String getReturnType() {
		return returnType;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public List<ModelBeanParameter> getParameterList() {
		return parameterList;
	}

}
