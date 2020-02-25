package com.robindrew.codegenerator.model.object.common;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelMethod extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute(required = false)
	private String returnType = "void";
	@Attribute(required = false)
	private String returnValue = null;
	@ElementList(entry = "Parameter", inline = true, required = false)
	private List<ModelMethodParameter> parameterList = new ArrayList<ModelMethodParameter>();

	public String getName() {
		return name;
	}

	public String getReturnType() {
		return returnType;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public List<ModelMethodParameter> getParameterList() {
		return parameterList;
	}

}
