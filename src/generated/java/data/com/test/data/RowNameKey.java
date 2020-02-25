package com.test.data;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RowNameKey implements IRowNameKey {

	/** The id field. */
	private int id = 0;
	/** The name field. */
	private String name = null;

	/**
	 * The empty constructor.
	 */
	public RowNameKey() {
	}

	/**
	 * The fields constructor.
	 */
	public RowNameKey(int id, String name) {
		setId(id);
		setName(name);
	}

	/**
	 * The clone constructor.
	 */
	public RowNameKey(IRowNameKey clone) {
		setId(clone.getId());
		setName(clone.getName());
	}

	/**
	 * The clone constructor.
	 */
	public RowNameKey(IRow clone) {
		setId(clone.getId());
		setName(clone.getName());
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
		if (name.length() < 6) {
			throw new IllegalArgumentException("name too short, minimum of 6 characters, value: '" + name + "'");
		}
		if (name.length() > 200) {
			throw new IllegalArgumentException("name too long, maximum of 200 characters, value: '" + name + "'");
		}
		this.name = name;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getId());
		builder.append(getName());
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
		IRowNameKey that = (IRowNameKey) object;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getId(), that.getId());
		builder.append(this.getName(), that.getName());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getId());
		builder.append(getName());
		return builder.toString();
	}

	@Override
	public int compareTo(IRowNameKey that) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(this.getId(), that.getId());
		builder.append(this.getName(), that.getName());
		return builder.toComparison();
	}
}
