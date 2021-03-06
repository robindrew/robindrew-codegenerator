package com.robindrew.codegenerator.model.object.sql;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import com.robindrew.codegenerator.model.object.IModelObject;
import com.robindrew.codegenerator.model.object.SimpleModelElement;
import com.robindrew.codegenerator.model.object.name.ModelName;

public class ModelResultSetParser extends SimpleModelElement implements IModelObject {

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

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		if (name == null) {
			return type + "ResultSetParser";
		}
		return name;
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

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id=" + id);
		}
		this.id = id;
	}

}
