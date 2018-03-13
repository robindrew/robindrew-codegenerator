package com.test.data;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RowDimensions implements IRowDimensions {

	/** The id field. */
	private int id = 0;
	/** The width field. */
	private long width = 0l;
	/** The height field. */
	private long height = 0l;

	/**
	 * The empty constructor.
	 */
	public RowDimensions() {
	}

	/**
	 * The fields constructor.
	 */
	public RowDimensions(int id, long width, long height) {
		setId(id);
		setWidth(width);
		setHeight(height);
	}

	/**
	 * The clone constructor.
	 */
	public RowDimensions(IRowDimensions clone) {
		setId(clone.getId());
		setWidth(clone.getWidth());
		setHeight(clone.getHeight());
	}

	/**
	 * The clone constructor.
	 */
	public RowDimensions(IRow clone) {
		setId(clone.getId());
		setWidth(clone.getWidth());
		setHeight(clone.getHeight());
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
	 * Getter for the id field.
	 * @return the value of the id field.
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Getter for the width field.
	 * @return the value of the width field.
	 */
	@Override
	public long getWidth() {
		return width;
	}

	/**
	 * Getter for the height field.
	 * @return the value of the height field.
	 */
	@Override
	public long getHeight() {
		return height;
	}

	/**
	 * Setter for the id field.
	 * @param id the id value to set.
	 */
	@Override
	public void setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id too small, minimum of 1, value: '" + id + "'");
		}
		this.id = id;
	}

	/**
	 * Setter for the width field.
	 * @param width the width value to set.
	 */
	@Override
	public void setWidth(long width) {
		this.width = width;
	}

	/**
	 * Setter for the height field.
	 * @param height the height value to set.
	 */
	@Override
	public void setHeight(long height) {
		this.height = height;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getId());
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
		IRowDimensions that = (IRowDimensions) object;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getId(), that.getId());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getId());
		builder.append(getWidth());
		builder.append(getHeight());
		return builder.toString();
	}

	@Override
	public int compareTo(IRowDimensions that) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(this.getId(), that.getId());
		return builder.toComparison();
	}
}
