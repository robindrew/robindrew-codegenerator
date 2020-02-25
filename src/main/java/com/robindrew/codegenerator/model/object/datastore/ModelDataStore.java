package com.robindrew.codegenerator.model.object.datastore;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import com.robindrew.codegenerator.model.object.ModelObject;
import com.robindrew.codegenerator.model.object.common.ModelExtends;
import com.robindrew.codegenerator.model.object.common.ModelMethod;
import com.robindrew.codegenerator.model.object.name.ModelName;

public class ModelDataStore extends ModelObject {

	@Attribute(required = false)
	private boolean map = true;
	@Attribute(required = false)
	private boolean copy = true;
	@Attribute(required = false)
	private boolean concurrent = true;
	@Attribute(required = false)
	private boolean delegate = true;
	@Attribute(required = false)
	private boolean writeBehind = true;
	@Attribute(required = false)
	private boolean sql = true;
	@Attribute(required = false)
	private boolean cachedPersister = true;
	@Attribute(required = false)
	private boolean immutable = false;
	@Attribute
	private String element;
	@Attribute
	private String key;
	@ElementList(entry = "Extends", inline = true, required = false)
	private List<ModelExtends> extendsList = new ArrayList<ModelExtends>();
	@ElementList(entry = "Row", inline = true, required = false)
	private List<ModelDataStoreRow> rowList = new ArrayList<ModelDataStoreRow>();
	@ElementList(entry = "Key", inline = true, required = false)
	private List<ModelDataStoreKey> keyList = new ArrayList<ModelDataStoreKey>();
	@Element(name = "ReadOnlyName", required = false)
	private ModelName readOnlyName = new ModelName("I", "ReadOnly");
	@ElementList(entry = "Method", inline = true, required = false)
	private List<ModelMethod> methodList = new ArrayList<ModelMethod>();

	public String getElement() {
		return element;
	}

	public String getKey() {
		return key;
	}

	public List<ModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<ModelDataStoreRow> getRowList() {
		return rowList;
	}

	public List<ModelDataStoreKey> getKeyList() {
		return keyList;
	}

	public List<ModelMethod> getMethodList() {
		return methodList;
	}

	public boolean isConcurrent() {
		return concurrent;
	}

	public boolean isDelegate() {
		return delegate;
	}

	public boolean isWriteBehind() {
		return writeBehind;
	}

	public boolean isMap() {
		return map;
	}

	public boolean isCopy() {
		return copy;
	}

	public boolean isImmutable() {
		return immutable;
	}

	public boolean isCachedPersister() {
		return cachedPersister;
	}

	public boolean isSql() {
		return sql;
	}

	public ModelName getReadOnlyName() {
		return readOnlyName;
	}

}
