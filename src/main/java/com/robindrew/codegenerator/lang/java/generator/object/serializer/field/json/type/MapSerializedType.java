package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type;

import com.robindrew.codegenerator.api.serializer.json.serializer.collection.MapSerializer;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;

public class MapSerializedType extends JsonSerializedType {

	private final JsonSerializedType key;
	private final JsonSerializedType value;

	public MapSerializedType(IJavaType type, JsonSerializedType key, JsonSerializedType value) {
		super(type);
		this.key = key;
		this.value = value;
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
		String keyName = key.getType().getSimpleName();
		String valueName = value.getType().getSimpleName();
		return "new MapSerializer<" + keyName + ", " + valueName + ">(" + key.getNewSerializer() + ", " + value.getNewSerializer() + ")";
	}
}
