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
	 * Setter for the id field.
	 * @param id the id value to set.
	 */
	void setId(int id);

	/**
	 * Setter for the name field.
	 * @param name the name value to set.
	 */
	void setName(String name);

	/**
	 * Setter for the data field.
	 * @param data the data value to set.
	 */
	void setData(byte[] data);

	/**
	 * Setter for the width field.
	 * @param width the width value to set.
	 */
	void setWidth(long width);

	/**
	 * Setter for the height field.
	 * @param height the height value to set.
	 */
	void setHeight(long height);

	/**
	 * Setter for the unit field.
	 * @param unit the unit value to set.
	 */
	void setUnit(TimeUnit unit);
}
