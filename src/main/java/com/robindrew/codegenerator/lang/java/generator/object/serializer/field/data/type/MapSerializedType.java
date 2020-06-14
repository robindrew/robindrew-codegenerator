package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.data.type;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.common.io.data.serializer.collection.MapSerializer;

public class MapSerializedType extends DataSerializedType {

	private final DataSerializedType key;
	private final DataSerializedType value;
	private boolean nullableKeys = false;
	private boolean nullableValues = false;

	public MapSerializedType(IJavaType type, DataSerializedType key, DataSerializedType value) {
		super(type);
		this.key = key;
		this.value = value;
	}

	public boolean isNullableKeys() {
		return nullableKeys;
	}

	public void setNullableKeys(boolean nullableKeys) {
		this.nullableKeys = nullableKeys;
	}

	public boolean isNullableValues() {
		return nullableValues;
	}

	public void setNullableValues(boolean nullableValues) {
		this.nullableValues = nullableValues;
	}

	@Override
	public void addReferences(IJavaTypeSet set) {
		set.add(getType());
		set.add(MapSerializer.class);
		key.addReferences(set);
		value.addReferences(set);
	}

	@Override
	public String getNewSerializer() {
		key.setNullable(isNullableKeys());
		value.setNullable(isNullableValues());
		String keyName = key.getType().getSimpleName();
		String valueName = value.getType().getSimpleName();
		return "new MapSerializer<" + keyName + ", " + valueName + ">(" + key.getNewSerializer() + ", " + value.getNewSerializer() + ", " + isNullable() + ")";
	}
}
