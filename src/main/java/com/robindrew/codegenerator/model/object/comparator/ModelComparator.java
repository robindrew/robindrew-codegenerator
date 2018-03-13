package com.robindrew.codegenerator.model.object.comparator;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelComparator extends ModelObject {

	@Attribute
	private String type;
	@Attribute(required = false)
	private boolean swap = true;
	@Attribute(required = false)
	private boolean reverse = true;
	@ElementList(entry = "Field", inline = true)
	private List<ModelComparatorField> fieldList = new ArrayList<ModelComparatorField>();

	public String getType() {
		return type;
	}

	public boolean isSwap() {
		return swap;
	}

	public boolean isReverse() {
		return reverse;
	}

	public List<ModelComparatorField> getFieldList() {
		return fieldList;
	}

}
