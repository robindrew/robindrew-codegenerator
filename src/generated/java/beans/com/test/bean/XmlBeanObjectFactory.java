package com.test.bean;

import com.robindrew.codegenerator.api.factory.IObjectFactory;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class XmlBeanObjectFactory implements IObjectFactory<IXmlSerializer<?>> {

	private final Map<Object, Class<? extends IXmlSerializer<?>>> objectMap = new ConcurrentHashMap<Object, Class<? extends IXmlSerializer<?>>>();;

	public XmlBeanObjectFactory() {

		// Mappings by id
		objectMap.put(MutableBean.SERIALIZATION_ID, MutableBeanXmlSerializer.class);

		// Mappings by name
		objectMap.put("MutableBean", MutableBeanXmlSerializer.class);
	}

	@Override
	public Set<Class<?>> getTypes() {
		return new HashSet<Class<?>>(objectMap.values());
	}

	@Override
	public boolean containsType(Object key) {
		return objectMap.containsKey(key);
	}

	@Override
	public Class<? extends IXmlSerializer<?>> getType(Object key) {
		if (key == null) {
			throw new NullPointerException("key");
		}
		Class<? extends IXmlSerializer<?>> type = objectMap.get(key);
		if (type == null) {
			throw new IllegalArgumentException("key: '" + key + "'");
		}
		return type;
	}

	@Override
	public IXmlSerializer<?> newInstance(Object key) {
		Class<? extends IXmlSerializer<?>> type = getType(key);
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new IllegalStateException("Unable to instantiate " + type, e);
		}
	}
}
