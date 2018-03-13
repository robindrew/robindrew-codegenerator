package com.robindrew.codegenerator.model.object;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

import com.robindrew.codegenerator.model.object.name.ModelName;

public class ModelObject extends SimpleModelElement implements IModelObject {

	@Attribute(required = false)
	private volatile int id;
	@Attribute
	private String name;
	@Attribute(required = false)
	private String target = null;
	@Element(name = "InterfaceName", required = false)
	private ModelName interfaceName = ModelName.prefix("I");

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getTarget() {
		return target;
	}

	public ModelName getInterfaceName() {
		return interfaceName;
	}

	@Override
	public void setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id=" + id);
		}
		this.id = id;
	}

}
