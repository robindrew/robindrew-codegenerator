package com.test.data;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Row implements IRow {

	/** The id field. */
	private int id = 0;
	/** The name field. */
	private String name = null;
	/** The data field. */
	private byte[] data = null;
	/** The width field. */
	private long width = 0l;
	/** The height field. */
	private long height = 0l;
	/** The unit field. */
	private TimeUnit unit = null;

	/**
	 * The empty constructor.
	 */
	public Row() {
	}

	/**
	 * The fields constructor.
	 */
	public Row(int id, String name, byte[] data, long width, long height, TimeUnit unit) {
		setId(id);
		setName(name);
		setData(data);
		setWidth(width);
		setHeight(height);
		setUnit(unit);
	}

	/**
	 * The clone constructor.
	 */
	public Row(IRow clone) {
		setId(clone.getId());
		setName(clone.getName());
		setData(clone.getData());
		setWidth(clone.getWidth());
		setHeight(clone.getHeight());
		setUnit(clone.getUnit());
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
	 * Getter for the name field.
	 * @return the value of the name field.
	 */
	@Override
	public String getName() {
		return name;
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
	 * Getter for the unit field.
	 * @return the value of the unit field.
	 */
	@Override
	public TimeUnit getUnit() {
		return unit;
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
	 * Setter for the name field.
	 * @param name the name value to set.
	 */
	@Override
	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("name too short, minimum of 3 characters, value: '" + name + "'");
		}
		if (name.length() > 200) {
			throw new IllegalArgumentException("name too long, maximum of 200 characters, value: '" + name + "'");
		}
		this.name = name;
	}

	/**
	 * Setter for the data field.
	 * @param data the data value to set.
	 */
	@Override
	public void setData(byte[] data) {
		this.data = data;
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

	/**
	 * Setter for the unit field.
	 * @param unit the unit value to set.
	 */
	@Override
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
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
		IRow that = (IRow) object;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getId(), that.getId());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getId());
		builder.append(getName());
		builder.append(getData());
		builder.append(getWidth());
		builder.append(getHeight());
		builder.append(getUnit());
		return builder.toString();
	}

	@Override
	public int compareTo(IRow that) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(this.getId(), that.getId());
		return builder.toComparison();
	}
}
