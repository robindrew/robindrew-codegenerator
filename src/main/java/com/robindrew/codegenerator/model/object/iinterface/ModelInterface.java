package com.robindrew.codegenerator.model.object.iinterface;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelInterface extends ModelObject {

	@ElementList(entry = "Extends", inline = true, required = false)
	private List<ModelExtends> extendsList = new ArrayList<ModelExtends>();
	@ElementList(entry = "Method", inline = true, required = false)
	private List<ModelInterfaceMethod> methodList = new ArrayList<ModelInterfaceMethod>();

	public List<ModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<ModelInterfaceMethod> getMethodList() {
		return methodList;
	}

}
