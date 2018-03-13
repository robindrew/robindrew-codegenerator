package com.robindrew.codegenerator.lang.java.generator.model.lookup;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.model.bean.JavaModelBean;
import com.robindrew.codegenerator.lang.java.generator.model.datastore.JavaModelDataStore;
import com.robindrew.codegenerator.lang.java.generator.model.eenum.JavaModelEnum;
import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelInterface;
import com.robindrew.codegenerator.lang.java.generator.model.serializer.JavaModelDataSerializer;
import com.robindrew.codegenerator.lang.java.generator.model.sql.JavaModelResultSetParser;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.type.IJavaType;

public interface IJavaModelSet {

	List<JavaModel> getModelList();

	List<JavaModelBean> getBeanList();

	List<JavaModelEnum> getEnumList();

	List<JavaModelResultSetParser> getResultSetParserList();

	List<JavaModelValidator> getValidatorList();

	List<JavaModelDataSerializer> getSerializerList();

	List<JavaModelDataStore> getDataStoreList();

	List<JavaModelInterface> getInterfaceList();

	JavaModelValidator getValidator(String name, boolean optional);

	JavaModelInterface getInterface(String name, boolean optional);

	JavaModelInterface getInterface(IJavaType type, boolean optional);

	JavaModelBean getBean(String name, boolean optional);

	JavaModelBean getBean(IJavaType type, boolean optional);

	JavaModelEnum getEnum(String name, boolean optional);

	JavaModelEnum getEnum(IJavaType type, boolean optional);

	JavaModelResultSetParser getResultSetParser(String name, boolean optional);

}