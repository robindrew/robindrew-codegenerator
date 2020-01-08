package com.robindrew.codegenerator.lang.java.generator.model.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robindrew.codegenerator.model.IModel;
import com.robindrew.codegenerator.model.object.bean.ModelBean;
import com.robindrew.codegenerator.model.object.option.ModelOption;
import com.robindrew.codegenerator.model.object.serializer.ModelDataSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelJsonSerializer;
import com.robindrew.codegenerator.model.object.serializer.ModelXmlSerializer;
import com.robindrew.codegenerator.model.object.sql.ModelResultSetParser;

public class OptionProcessor {

	private static final Logger log = LoggerFactory.getLogger(OptionProcessor.class);

	public void processOptions(IModel model) {
		// Options (process these immediately)
		for (ModelOption option : model.getOptionList()) {
			processOptions(option.getValue(), model);
		}
	}

	public void processOptions(String option, IModel model) {
		switch (option) {
			case "AutoGenerateXmlSerializers":
				autoGenerateXmlSerializers(model);
				return;
			case "AutoGenerateJsonSerializers":
				autoGenerateJsonSerializers(model);
				return;
			case "AutoGenerateDataSerializers":
				autoGenerateDataSerializers(model);
				return;
			case "AutoGenerateResultSetParsers":
				autoGenerateResultSetParsers(model);
				return;
			default:
				throw new IllegalArgumentException("Unknown option: '" + option + "'");
		}
	}

	public void autoGenerateXmlSerializers(IModel model) {
		log.info("Applying Option: AutoGenerateXmlSerializers");
		for (ModelBean bean : model.getBeanList()) {
			ModelXmlSerializer serializer = new ModelXmlSerializer();
			serializer.setType(bean.getName());
			if (bean.getReturnType() != null) {
				serializer.setReturnType(true);
			}
			log.info("Auto Generated Xml Serializer:\n" + serializer);
			model.getXmlSerializerList().add(serializer);
		}
	}

	public void autoGenerateJsonSerializers(IModel model) {
		log.info("Applying Option: AutoGenerateJsonSerializers");
		for (ModelBean bean : model.getBeanList()) {
			ModelJsonSerializer serializer = new ModelJsonSerializer();
			serializer.setType(bean.getName());
			if (bean.getReturnType() != null) {
				serializer.setReturnType(true);
			}
			log.info("Auto Generated Json Serializer:\n" + serializer);
			model.getJsonSerializerList().add(serializer);
		}
	}

	public void autoGenerateDataSerializers(IModel model) {
		log.info("Applying Option: AutoGenerateDataSerializers");
		for (ModelBean bean : model.getBeanList()) {
			ModelDataSerializer serializer = new ModelDataSerializer();
			serializer.setType(bean.getName());
			if (bean.getReturnType() != null) {
				serializer.setReturnType(true);
			}
			log.info("Auto Generated Data Serializer:\n" + serializer);
			model.getDataSerializerList().add(serializer);
		}
	}

	private void autoGenerateResultSetParsers(IModel model) {
		log.info("Applying Option: AutoGenerateResultSetParsers");
		for (ModelBean bean : model.getBeanList()) {
			ModelResultSetParser parser = new ModelResultSetParser();
			parser.setType(bean.getName());
			log.info("Auto Generated ResultSet Parser:\n" + parser);
			model.getResultSetParserList().add(parser);
		}
	}

}