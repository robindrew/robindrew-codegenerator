package com.robindrew.codegenerator.model.object.bean;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.SimpleModelElement;

public class ModelBeanField extends SimpleModelElement {

	@Attribute
	private String name;
	@Attribute(required = false)
	private String type;
	@Attribute(required = false)
	private boolean identity = false;
	@Attribute(required = false)
	private boolean unique = false;
	@Attribute(required = false)
	private boolean autoIncrement = false;
	@Attribute(required = false)
	private String value = null;
	@ElementList(entry = "Annotation", inline = true, required = false)
	private List<ModelBeanAnnotation> annotationList = new ArrayList<ModelBeanAnnotation>();

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public boolean isIdentity() {
		return identity;
	}

	public String getValue() {
		return value;
	}

	public boolean hasValue() {
		return value != null;
	}

	public List<ModelBeanAnnotation> getAnnotationList() {
		return annotationList;
	}

	public boolean isUnique() {
		return unique;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}
}
