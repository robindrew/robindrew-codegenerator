package com.test.data;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Row implements IRow {

	/** The id field. */
	private final int id;
	/** The name field. */
	private final String name;
	/** The data field. */
	private final byte[] data;
	/** The width field. */
	private final long width;
	/** The height field. */
	private final long height;
	/** The unit field. */
	private final TimeUnit unit;

	/**
	 * The fields constructor.
	 */
	public Row(int id, String name, byte[] data, long width, long height, TimeUnit unit) {
		if (id < 1) {
			throw new IllegalArgumentException("id too small, minimum of 1, value: '" + id + "'");
		}
		if (name == null) {
			throw new NullPointerException("name");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("name too short, minimum of 3 characters, value: '" + name + "'");
		}
		if (name.length() > 200) {
			throw new IllegalArgumentException("name too long, maximum of 200 characters, value: '" + name + "'");
		}
		this.id = id;
		this.name = name;
		this.data = data;
		this.width = width;
		this.height = height;
		this.unit = unit;
	}

	/**
	 * The clone constructor.
	 */
	public Row(IRow clone) {
		this.id = clone.getId();
		this.name = clone.getName();
		this.data = clone.getData();
		this.width = clone.getWidth();
		this.height = clone.getHeight();
		this.unit = clone.getUnit();
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
	 * Setter for the id field, return a copy with the field set.
	 * @param id the id value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IRow setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id too small, minimum of 1, value: '" + id + "'");
		}
		return new Row(id, name, data, width, height, unit);
	}

	/**
	 * Setter for the name field, return a copy with the field set.
	 * @param name the name value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IRow setName(String name) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		if (name.length() < 3) {
			throw new IllegalArgumentException("name too short, minimum of 3 characters, value: '" + name + "'");
		}
		if (name.length() > 200) {
			throw new IllegalArgumentException("name too long, maximum of 200 characters, value: '" + name + "'");
		}
		return new Row(id, name, data, width, height, unit);
	}

	/**
	 * Setter for the data field, return a copy with the field set.
	 * @param data the data value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IRow setData(byte[] data) {
		return new Row(id, name, data, width, height, unit);
	}

	/**
	 * Setter for the width field, return a copy with the field set.
	 * @param width the width value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IRow setWidth(long width) {
		return new Row(id, name, data, width, height, unit);
	}

	/**
	 * Setter for the height field, return a copy with the field set.
	 * @param height the height value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IRow setHeight(long height) {
		return new Row(id, name, data, width, height, unit);
	}

	/**
	 * Setter for the unit field, return a copy with the field set.
	 * @param unit the unit value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IRow setUnit(TimeUnit unit) {
		return new Row(id, name, data, width, height, unit);
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
