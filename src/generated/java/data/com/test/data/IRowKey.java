package com.test.data;

import com.robindrew.codegenerator.api.bean.IBean;

public interface IRowKey extends IBean, Comparable<IRowKey> {

	int SERIALIZATION_ID = 2002;

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
	 * Setter for the id field.
	 * @param id the id value to set.
	 */
	void setId(int id);
}
