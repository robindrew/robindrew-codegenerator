package com.test.data;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RowData implements IRowData {

	/** The data field. */
	private byte[] data = null;

	/**
	 * The empty constructor.
	 */
	public RowData() {
	}

	/**
	 * The fields constructor.
	 */
	public RowData(byte[] data) {
		setData(data);
	}

	/**
	 * The clone constructor.
	 */
	public RowData(IRowData clone) {
		setData(clone.getData());
	}

	/**
	 * The clone constructor.
	 */
	public RowData(IRow clone) {
		setData(clone.getData());
	}

	/**
	 * Returns the serialization id.
	 * @return the serialization id.
	 */
	@Override
	public int getSerializationId() {
		return SERIALIZATION_ID;
	}

	/**
	 * Getter for the data field.
	 * @return the value of the data field.
	 */
	@Override
	public byte[] getData() {
		return data;
	}

	/**
	 * Setter for the data field.
	 * @param data the data value to set.
	 */
	@Override
	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getData());
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		// Identity check
		if (object == this) {
			return true;
		}

		// Null check
		if (object == null) {
			return false;
		}

		// Compare types
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}

		// Compare fields
		IRowData that = (IRowData) object;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getData(), that.getData());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getData());
		return builder.toString();
	}

	@Override
	public int compareTo(IRowData that) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(this.getData(), that.getData());
		return builder.toComparison();
	}
}
