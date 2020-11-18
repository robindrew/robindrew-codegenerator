package com.robindrew.codegenerator.model;

import java.util.List;

import com.robindrew.codegenerator.model.object.adapter.ModelAdapter;
import com.robindrew.codegenerator.model.object.alias.ModelAlias;
import com.robindrew.codegenerator.model.object.annotation.ModelAnnotation;
import com.robindrew.codegenerator.model.object.bean.ModelBean;
import com.robindrew.codegenerator.model.object.builder.ModelBuilder;
import com.robindrew.codegenerator.model.object.comparator.ModelComparator;
import com.robindrew.codegenerator.model.object.datastore.ModelDataStore;
import com.robindrew.codegenerator.model.object.delegate.ModelDelegate;
import com.robindrew.codegenerator.model.object.eenum.ModelEnum;
import com.robindrew.codegenerator.model.object.executor.ModelExecutorSet;
import com.robindrew.codegenerator.model.object.factory.ModelObjectFactory;
import com.robindrew.codegenerator.model.object.iinterface.ModelInterface;
import com.robindrew.codegenerator.model.object.option.ModelOption;
import com.robindrew.codegenerator.model.object.serializer.ModelDataSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelJsonSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelXmlSerializer;
import com.robindrew.codegenerator.model.object.servlet.ModelServletRequestParser;
import com.robindrew.codegenerator.model.object.sql.ModelResultSetParser;
import com.robindrew.codegenerator.model.object.validator.ModelValidator;

public interface IModel {

	int getId();

	String getPackage();

	String getTarget();

	List<ModelOption> getOptionList();

	List<ModelAlias> getAliasList();

	List<ModelValidator> getValidatorList();

	List<ModelBean> getBeanList();

	List<ModelEnum> getEnumList();

	List<ModelAdapter> getAdapterList();

	List<ModelComparator> getComparatorList();

	List<ModelInterface> getInterfaceList();

	List<ModelObjectFactory> getObjectFactoryList();

	List<ModelExecutorSet> getExecutorSetList();

	List<ModelDataSerializer> getDataSerializerList();

	List<ModelXmlSerializer> getXmlSerializerList();

	List<ModelJsonSerializer> getJsonSerializerList();

	List<ModelDataStore> getDataStoreList();

	List<ModelDelegate> getDelegateList();

	List<ModelResultSetParser> getResultSetParserList();

	List<ModelAnnotation> getAnnotationList();

	List<ModelServletRequestParser> getServletRequestParserList();

	List<ModelBuilder> getBuilderList();

}
