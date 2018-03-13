package com.robindrew.codegenerator.lang.java.generator.object.bean.lookup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.bean.ModelBean;

public class JavaBeanLookup implements IJavaBeanLookup {

	private final Map<IJavaType, ModelBean> map = new ConcurrentHashMap<IJavaType, ModelBean>();

	@Override
	public ModelBean getBean(IJavaType type) {
		ModelBean bean = map.get(type);
		if (bean == null) {
			throw new IllegalArgumentException("bean not found for type: " + type);
		}
		return bean;
	}

	@Override
	public void setBean(IJavaType type, ModelBean bean) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		if (bean == null) {
			throw new NullPointerException("bean");
		}
		map.put(type, bean);
	}

}
