package com.robindrew.codegenerator.model.object.datastore;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelDataStorePrimaryKey extends SimpleModelElement {

	@Attribute
	private String name;
	@ElementList(entry = "Field", inline = true)
	private List<ModelDataStoreField> fieldList = new ArrayList<ModelDataStoreField>();

	public List<ModelDataStoreField> getFieldList() {
		return fieldList;
	}

	public String getName() {
		return name;
	}

}
