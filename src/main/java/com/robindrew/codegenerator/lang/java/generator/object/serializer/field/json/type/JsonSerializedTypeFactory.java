package com.robindrew.codegenerator.lang.java.generator.object.serializer.field.json.type;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.robindrew.codegenerator.api.serializer.json.serializer.array.BooleanArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.ByteArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.CharArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.DoubleArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.FloatArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.IntArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.LongArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.array.ShortArraySerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.BooleanSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.ByteSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.CharacterSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.DoubleSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.FloatSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.IntegerSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.LongSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.ShortSerializer;
import com.robindrew.codegenerator.api.serializer.json.serializer.lang.StringSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.lookup.IJavaModelSet;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public class JsonSerializedTypeFactory {

	private final IJavaModelSet models;

	public JsonSerializedTypeFactory(IJavaModelSet models) {
		this.models = models;
	}

	public JsonSerializedType getType(IJavaType type) {

		// Arrays
		if (type.isArray()) {
			return getArrayType(type);
		}

		// Primitive
		if (type.isType(Character.class)) {
			return new ObjectSerializedType(type, CharacterSerializer.class);
		}
		if (type.isType(Boolean.class)) {
			return new ObjectSerializedType(type, BooleanSerializer.class);
		}
		if (type.isType(Byte.class)) {
			return new ObjectSerializedType(type, ByteSerializer.class);
		}
		if (type.isType(Short.class)) {
			return new ObjectSerializedType(type, ShortSerializer.class);
		}
		if (type.isType(Integer.class)) {
			return new ObjectSerializedType(type, IntegerSerializer.class);
		}
		if (type.isType(Long.class)) {
			return new ObjectSerializedType(type, LongSerializer.class);
		}
		if (type.isType(Float.class)) {
			return new ObjectSerializedType(type, FloatSerializer.class);
		}
		if (type.isType(Double.class)) {
			return new ObjectSerializedType(type, DoubleSerializer.class);
		}

		// Enum
		if (type.isInstanceOf(Enum.class)) {
			return new EnumSerializedType(type);
		}

		// String
		if (type.isType(String.class)) {
			return new ObjectSerializedType(type, StringSerializer.class);
		}

		// List
		if (type.isType(List.class)) {
			IJavaType component = type.getGenericsList().get(0);
			JsonSerializedType componentType = getType(component);
			return new ListSerializedType(type, componentType);
		}

		// Set
		if (type.isType(Set.class)) {
			IJavaType component = type.getGenericsList().get(0);
			JsonSerializedType componentType = getType(component);
			return new SetSerializedType(type, componentType);
		}

		// Map
		if (type.isType(Map.class)) {
			JsonSerializedType key = getType(type.getGenericsList().get(0));
			JsonSerializedType value = getType(type.getGenericsList().get(1));
			return new MapSerializedType(type, key, value);
		}

		// Enum
		JavaModelEnum eenum = models.getEnum(type, true);
		if (eenum != null) {
			return new EnumSerializedType(type);
		}

		// Bean
		JavaModelBean bean = models.getBean(type, false);
		return new ObjectSerializedType(type, bean.getJsonSerializer().getType());
	}

	private JsonSerializedType getArrayType(IJavaType type) {

		// TODO: Mulitple dimension array support
		if (type.isType(char[].class)) {
			return new ObjectSerializedType(type, CharArraySerializer.class);
		}
		if (type.isType(boolean[].class)) {
			return new ObjectSerializedType(type, BooleanArraySerializer.class);
		}
		if (type.isType(byte[].class)) {
			return new ObjectSerializedType(type, ByteArraySerializer.class);
		}
		if (type.isType(short[].class)) {
			return new ObjectSerializedType(type, ShortArraySerializer.class);
		}
		if (type.isType(int[].class)) {
			return new ObjectSerializedType(type, IntArraySerializer.class);
		}
		if (type.isType(long[].class)) {
			return new ObjectSerializedType(type, LongArraySerializer.class);
		}
		if (type.isType(float[].class)) {
			return new ObjectSerializedType(type, FloatArraySerializer.class);
		}
		if (type.isType(double[].class)) {
			return new ObjectSerializedType(type, DoubleArraySerializer.class);
		}

		// Object[]
		IJavaType component = type.getComponentType();
		JsonSerializedType componentType = getType(component);
		return new ObjectArraySerializedType(type, componentType, component);
	}

}
