package com.robindrew.codegenerator.model.object.iinterface;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelInterfaceMethod extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute(required = false)
	private String returnType = "void";
	@ElementList(entry = "Parameter", inline = true, required = false)
	private List<ModelInterfaceParameter> parameterList = new ArrayList<ModelInterfaceParameter>();

	public String getName() {
		return name;
	}

	public String getReturnType() {
		return returnType;
	}

	public List<ModelInterfaceParameter> getParameterList() {
		return parameterList;
	}

}
