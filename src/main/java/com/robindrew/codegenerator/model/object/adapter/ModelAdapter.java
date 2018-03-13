package com.robindrew.codegenerator.model.object.adapter;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelAdapter extends ModelObject {

	@Attribute
	private String from;
	@Attribute
	private String to;
	@ElementList(entry = "Field", inline = true)
	private List<ModelAdapterField> fieldList = new ArrayList<ModelAdapterField>();

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public List<ModelAdapterField> getFieldList() {
		return fieldList;
	}

}
