package com.robindrew.codegenerator.model.object.executor;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;
import com.robindrew.codegenerator.model.object.common.ModelExtends;

public class ModelExecutorSet extends ModelObject {

	@ElementList(entry = "Extends", inline = true, required = false)
	private List<ModelExtends> extendsList = new ArrayList<ModelExtends>();

	public List<ModelExtends> getExtendsList() {
		return extendsList;
	}
}
