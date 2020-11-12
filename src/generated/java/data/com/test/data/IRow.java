package com.test.data;

import com.robindrew.codegenerator.api.bean.IBean;
import java.util.concurrent.TimeUnit;

public interface IRow extends IBean, Comparable<IRow> {

	int SERIALIZATION_ID = 2001;

	/**
	 * Returns the serialization id.
	 * @return the serialization id.
	 */
	int getSerializationId();

	/**
	 * Getter for the id field.
	 * @return the value of the id field.
	 */
	int getId();

	/**
	 * Getter for the name field.
	 * @return the value of the name field.
	 */
	String getName();

	/**
	 * Getter for the data field.
	 * @return the value of the data field.
	 */
	byte[] getData();

	/**
	 * Getter for the width field.
	 * @return the value of the width field.
	 */
	long getWidth();

	/**
	 * Getter for the height field.
	 * @return the value of the height field.
	 */
	long getHeight();

	/**
	 * Getter for the unit field.
	 * @return the value of the unit field.
	 */
	TimeUnit getUnit();

	/**
	 * Setter for the id field, return a copy with the field set.
	 * @param id the id value to set.
	 * @return a copy of this object.
	 */
	IRow setId(int id);

	/**
	 * Setter for the name field, return a copy with the field set.
	 * @param name the name value to set.
	 * @return a copy of this object.
	 */
	IRow setName(String name);

	/**
	 * Setter for the data field, return a copy with the field set.
	 * @param data the data value to set.
	 * @return a copy of this object.
	 */
	IRow setData(byte[] data);

	/**
	 * Setter for the width field, return a copy with the field set.
	 * @param width the width value to set.
	 * @return a copy of this object.
	 */
	IRow setWidth(long width);

	/**
	 * Setter for the height field, return a copy with the field set.
	 * @param height the height value to set.
	 * @return a copy of this object.
	 */
	IRow setHeight(long height);

	/**
	 * Setter for the unit field, return a copy with the field set.
	 * @param unit the unit value to set.
	 * @return a copy of this object.
	 */
	IRow setUnit(TimeUnit unit);
}
