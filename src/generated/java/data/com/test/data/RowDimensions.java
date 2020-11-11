package com.test.data;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RowDimensions implements IRowDimensions {

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
	public RowDimensions(long width, long height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * The clone constructor.
	 */
	public RowDimensions(IRowDimensions clone) {
		setWidth(clone.getWidth());
		setHeight(clone.getHeight());
	}

	/**
	 * The clone constructor.
	 */
	public RowDimensions(IRow clone) {
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
		builder.append(getWidth());
		builder.append(getHeight());
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
		builder.append(this.getWidth(), that.getWidth());
		builder.append(this.getHeight(), that.getHeight());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getWidth());
		builder.append(getHeight());
		return builder.toString();
	}

	@Override
	public int compareTo(IRowDimensions that) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(this.getWidth(), that.getWidth());
		builder.append(this.getHeight(), that.getHeight());
		return builder.toComparison();
	}
}
