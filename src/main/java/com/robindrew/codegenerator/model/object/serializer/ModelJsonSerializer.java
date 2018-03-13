package com.robindrew.codegenerator.model.object.serializer;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import com.robindrew.codegenerator.model.object.IModelObject;
import com.robindrew.codegenerator.model.object.SimpleModelElement;
import com.robindrew.codegenerator.model.object.name.ModelName;

public class ModelJsonSerializer extends SimpleModelElement implements IModelObject {

	@Attribute(required = false)
	private volatile int id;
	@Attribute(required = false)
	private String name;
	@Attribute(required = false)
	private String target = null;
	@Element(name = "InterfaceName", required = false)
	private ModelName interfaceName = ModelName.prefix("I");
	@Attribute
	private String type;
	@Attribute(required = false)
	private boolean returnType = false;
	@Attribute(required = false)
	private String returnTypeName;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		if (name == null) {
			return type + "JsonSerializer";
		}
		return name;
	}

	public String getReturnTypeName() {
		if (returnTypeName == null) {
			return type + "ReturnTypeJsonSerializer";
		}
		return returnTypeName;
	}

	@Override
	public String getTarget() {
		return target;
	}

	public ModelName getInterfaceName() {
		return interfaceName;
	}

	public String getType() {
		return type;
	}

	public boolean isReturnType() {
		return returnType;
	}

	@Override
	public void setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id=" + id);
		}
		this.id = id;
	}

	public void setType(String type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	public void setReturnType(boolean returnType) {
		this.returnType = returnType;
	}

}
