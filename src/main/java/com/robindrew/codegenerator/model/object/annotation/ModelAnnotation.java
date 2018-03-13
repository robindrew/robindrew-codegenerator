package com.robindrew.codegenerator.model.object.annotation;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;

public class ModelAnnotation extends ModelObject {

	@ElementList(entry = "Field", inline = true)
	private List<ModelAnnotationField> fieldList = new ArrayList<ModelAnnotationField>();

	public List<ModelAnnotationField> getFieldList() {
		return fieldList;
	}

}
