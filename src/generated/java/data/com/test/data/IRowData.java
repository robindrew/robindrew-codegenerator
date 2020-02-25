package com.test.data;

import com.robindrew.codegenerator.api.bean.IBean;

public interface IRowData extends IBean, Comparable<IRowData> {

	int SERIALIZATION_ID = 2004;

	/**
	 * Returns the serialization id.
	 * @return the serialization id.
	 */
	int getSerializationId();

	/**
	 * Getter for the data field.
	 * @return the value of the data field.
	 */
	byte[] getData();

	/**
	 * Setter for the data field.
	 * @param data the data value to set.
	 */
	void setData(byte[] data);
}
