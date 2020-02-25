package com.robindrew.codegenerator.model.object.eenum;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;
import com.robindrew.codegenerator.model.object.common.ModelExtends;

public class ModelEnum extends ModelObject {

	@ElementList(entry = "Constant", inline = true)
	private List<ModelEnumConstant> constantList = new ArrayList<ModelEnumConstant>();
	@ElementList(entry = "Extends", inline = true, required = false)
	private List<ModelExtends> extendsList = new ArrayList<ModelExtends>();

	public List<ModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<ModelEnumConstant> getConstantList() {
		return constantList;
	}

}
