package com.test.data;

import java.util.concurrent.TimeUnit;

public class RowBuilder {

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
	public RowBuilder() {
	}

	/**
	 * The fields constructor.
	 */
	public RowBuilder(int id, String name, byte[] data, long width, long height, TimeUnit unit) {
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
	public RowBuilder(IRow clone) {
		setId(clone.getId());
		setName(clone.getName());
		setData(clone.getData());
		setWidth(clone.getWidth());
		setHeight(clone.getHeight());
		setUnit(clone.getUnit());
	}

	/**
	 * Setter for the id field.
	 * @param id the id value to set.
	 */
	public RowBuilder setId(int id) {
		if (id < 1) {
			throw new IllegalArgumentException("id too small, minimum of 1, value: '" + id + "'");
		}
		this.id = id;
		return this;
	}

	/**
	 * Setter for the name field.
	 * @param name the name value to set.
	 */
	public RowBuilder setName(String name) {
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
		return this;
	}

	/**
	 * Setter for the data field.
	 * @param data the data value to set.
	 */
	public RowBuilder setData(byte[] data) {
		this.data = data;
		return this;
	}

	/**
	 * Setter for the width field.
	 * @param width the width value to set.
	 */
	public RowBuilder setWidth(long width) {
		this.width = width;
		return this;
	}

	/**
	 * Setter for the height field.
	 * @param height the height value to set.
	 */
	public RowBuilder setHeight(long height) {
		this.height = height;
		return this;
	}

	/**
	 * Setter for the unit field.
	 * @param unit the unit value to set.
	 */
	public RowBuilder setUnit(TimeUnit unit) {
		this.unit = unit;
		return this;
	}

	/**
	 * Build a new Row.
	 * @return a new Row.
	 */
	public IRow build() {
		return new Row(id, name, data, width, height, unit);
	}
}
