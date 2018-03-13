package com.robindrew.codegenerator.model.object.eenum;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelEnumConstant extends SimpleModelElement {

	@Attribute
	private String name;
	@ElementList(entry = "Field", inline = true, required = false)
	private List<ModelEnumField> fieldList = new ArrayList<ModelEnumField>();

	public String getName() {
		return name;
	}

	public List<ModelEnumField> getFieldList() {
		return fieldList;
	}

}
