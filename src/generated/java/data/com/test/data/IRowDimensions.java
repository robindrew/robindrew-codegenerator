package com.test.data;

import com.robindrew.codegenerator.api.bean.IBean;

public interface IRowDimensions extends IBean, Comparable<IRowDimensions> {

	int SERIALIZATION_ID = 2005;

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
	 * Setter for the id field.
	 * @param id the id value to set.
	 */
	void setId(int id);

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
}
