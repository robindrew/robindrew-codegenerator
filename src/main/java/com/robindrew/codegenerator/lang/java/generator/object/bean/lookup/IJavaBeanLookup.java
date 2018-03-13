package com.robindrew.codegenerator.lang.java.generator.object.bean.lookup;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.bean.ModelBean;

public interface IJavaBeanLookup {

	ModelBean getBean(IJavaType type);

	void setBean(IJavaType type, ModelBean bean);

}
