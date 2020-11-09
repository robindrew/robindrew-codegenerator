package com.test.bean;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ImmutableBean implements IImmutableBean {

	/** The valueChar field. */
	private final char valueChar;
	/** The valueBoolean field. */
	private final boolean valueBoolean;
	/** The valueByte field. */
	private final byte valueByte;
	/** The valueShort field. */
	private final short valueShort;
	/** The valueInt field. */
	private final int valueInt;
	/** The valueLong field. */
	private final long valueLong;
	/** The valueFloat field. */
	private final float valueFloat;
	/** The valueDouble field. */
	private final double valueDouble;
	/** The valueObjectCharacter field. */
	private final Character valueObjectCharacter;
	/** The valueObjectBoolean field. */
	private final Boolean valueObjectBoolean;
	/** The valueObjectByte field. */
	private final Byte valueObjectByte;
	/** The valueObjectShort field. */
	private final Short valueObjectShort;
	/** The valueObjectInteger field. */
	private final Integer valueObjectInteger;
	/** The valueObjectLong field. */
	private final Long valueObjectLong;
	/** The valueObjectFloat field. */
	private final Float valueObjectFloat;
	/** The valueObjectDouble field. */
	private final Double valueObjectDouble;
	/** The valueObjectString field. */
	private final String valueObjectString;
	/** The valueObjectBytes field. */
	private final byte[] valueObjectBytes;
	/** The valueObjectTimeUnit field. */
	private final TimeUnit valueObjectTimeUnit;
	/** The valueObjectList field. */
	private final List<String> valueObjectList;
	/** The valueObjectSet field. */
	private final Set<Integer> valueObjectSet;
	/** The valueObjectMap field. */
	private final Map<String, IMutableBean> valueObjectMap;
	/** The set field. */
	private final boolean set;

	/**
	 * The fields constructor.
	 */
	public ImmutableBean(char valueChar, boolean valueBoolean, byte valueByte, short valueShort, int valueInt, long valueLong, float valueFloat, double valueDouble, Character valueObjectCharacter, Boolean valueObjectBoolean, Byte valueObjectByte, Short valueObjectShort, Integer valueObjectInteger, Long valueObjectLong, Float valueObjectFloat, Double valueObjectDouble, String valueObjectString, byte[] valueObjectBytes, TimeUnit valueObjectTimeUnit, List<String> valueObjectList, Set<Integer> valueObjectSet, Map<String, IMutableBean> valueObjectMap, boolean set) {
		this.valueChar = valueChar;
		this.valueBoolean = valueBoolean;
		this.valueByte = valueByte;
		this.valueShort = valueShort;
		this.valueInt = valueInt;
		this.valueLong = valueLong;
		this.valueFloat = valueFloat;
		this.valueDouble = valueDouble;
		this.valueObjectCharacter = valueObjectCharacter;
		this.valueObjectBoolean = valueObjectBoolean;
		this.valueObjectByte = valueObjectByte;
		this.valueObjectShort = valueObjectShort;
		this.valueObjectInteger = valueObjectInteger;
		this.valueObjectLong = valueObjectLong;
		this.valueObjectFloat = valueObjectFloat;
		this.valueObjectDouble = valueObjectDouble;
		this.valueObjectString = valueObjectString;
		this.valueObjectBytes = valueObjectBytes;
		this.valueObjectTimeUnit = valueObjectTimeUnit;
		this.valueObjectList = valueObjectList;
		this.valueObjectSet = valueObjectSet;
		this.valueObjectMap = valueObjectMap;
		this.set = set;
	}

	/**
	 * The clone constructor.
	 */
	public ImmutableBean(IImmutableBean clone) {
		this.valueChar = clone.getValueChar();
		this.valueBoolean = clone.getValueBoolean();
		this.valueByte = clone.getValueByte();
		this.valueShort = clone.getValueShort();
		this.valueInt = clone.getValueInt();
		this.valueLong = clone.getValueLong();
		this.valueFloat = clone.getValueFloat();
		this.valueDouble = clone.getValueDouble();
		this.valueObjectCharacter = clone.getValueObjectCharacter();
		this.valueObjectBoolean = clone.getValueObjectBoolean();
		this.valueObjectByte = clone.getValueObjectByte();
		this.valueObjectShort = clone.getValueObjectShort();
		this.valueObjectInteger = clone.getValueObjectInteger();
		this.valueObjectLong = clone.getValueObjectLong();
		this.valueObjectFloat = clone.getValueObjectFloat();
		this.valueObjectDouble = clone.getValueObjectDouble();
		this.valueObjectString = clone.getValueObjectString();
		this.valueObjectBytes = clone.getValueObjectBytes();
		this.valueObjectTimeUnit = clone.getValueObjectTimeUnit();
		this.valueObjectList = clone.getValueObjectList();
		this.valueObjectSet = clone.getValueObjectSet();
		this.valueObjectMap = clone.getValueObjectMap();
		this.set = clone.getSet();
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
	 * Getter for the valueChar field.
	 * @return the value of the valueChar field.
	 */
	@Override
	public char getValueChar() {
		return valueChar;
	}

	/**
	 * Getter for the valueBoolean field.
	 * @return the value of the valueBoolean field.
	 */
	@Override
	public boolean getValueBoolean() {
		return valueBoolean;
	}

	/**
	 * Getter for the valueByte field.
	 * @return the value of the valueByte field.
	 */
	@Override
	public byte getValueByte() {
		return valueByte;
	}

	/**
	 * Getter for the valueShort field.
	 * @return the value of the valueShort field.
	 */
	@Override
	public short getValueShort() {
		return valueShort;
	}

	/**
	 * Getter for the valueInt field.
	 * @return the value of the valueInt field.
	 */
	@Override
	public int getValueInt() {
		return valueInt;
	}

	/**
	 * Getter for the valueLong field.
	 * @return the value of the valueLong field.
	 */
	@Override
	public long getValueLong() {
		return valueLong;
	}

	/**
	 * Getter for the valueFloat field.
	 * @return the value of the valueFloat field.
	 */
	@Override
	public float getValueFloat() {
		return valueFloat;
	}

	/**
	 * Getter for the valueDouble field.
	 * @return the value of the valueDouble field.
	 */
	@Override
	public double getValueDouble() {
		return valueDouble;
	}

	/**
	 * Getter for the valueObjectCharacter field.
	 * @return the value of the valueObjectCharacter field.
	 */
	@Override
	public Character getValueObjectCharacter() {
		return valueObjectCharacter;
	}

	/**
	 * Getter for the valueObjectBoolean field.
	 * @return the value of the valueObjectBoolean field.
	 */
	@Override
	public Boolean getValueObjectBoolean() {
		return valueObjectBoolean;
	}

	/**
	 * Getter for the valueObjectByte field.
	 * @return the value of the valueObjectByte field.
	 */
	@Override
	public Byte getValueObjectByte() {
		return valueObjectByte;
	}

	/**
	 * Getter for the valueObjectShort field.
	 * @return the value of the valueObjectShort field.
	 */
	@Override
	public Short getValueObjectShort() {
		return valueObjectShort;
	}

	/**
	 * Getter for the valueObjectInteger field.
	 * @return the value of the valueObjectInteger field.
	 */
	@Override
	public Integer getValueObjectInteger() {
		return valueObjectInteger;
	}

	/**
	 * Getter for the valueObjectLong field.
	 * @return the value of the valueObjectLong field.
	 */
	@Override
	public Long getValueObjectLong() {
		return valueObjectLong;
	}

	/**
	 * Getter for the valueObjectFloat field.
	 * @return the value of the valueObjectFloat field.
	 */
	@Override
	public Float getValueObjectFloat() {
		return valueObjectFloat;
	}

	/**
	 * Getter for the valueObjectDouble field.
	 * @return the value of the valueObjectDouble field.
	 */
	@Override
	public Double getValueObjectDouble() {
		return valueObjectDouble;
	}

	/**
	 * Getter for the valueObjectString field.
	 * @return the value of the valueObjectString field.
	 */
	@Override
	public String getValueObjectString() {
		return valueObjectString;
	}

	/**
	 * Getter for the valueObjectBytes field.
	 * @return the value of the valueObjectBytes field.
	 */
	@Override
	public byte[] getValueObjectBytes() {
		return valueObjectBytes;
	}

	/**
	 * Getter for the valueObjectTimeUnit field.
	 * @return the value of the valueObjectTimeUnit field.
	 */
	@Override
	public TimeUnit getValueObjectTimeUnit() {
		return valueObjectTimeUnit;
	}

	/**
	 * Getter for the valueObjectList field.
	 * @return the value of the valueObjectList field.
	 */
	@Override
	public List<String> getValueObjectList() {
		return valueObjectList;
	}

	/**
	 * Getter for the valueObjectSet field.
	 * @return the value of the valueObjectSet field.
	 */
	@Override
	public Set<Integer> getValueObjectSet() {
		return valueObjectSet;
	}

	/**
	 * Getter for the valueObjectMap field.
	 * @return the value of the valueObjectMap field.
	 */
	@Override
	public Map<String, IMutableBean> getValueObjectMap() {
		return valueObjectMap;
	}

	/**
	 * Getter for the set field.
	 * @return the value of the set field.
	 */
	@Override
	public boolean getSet() {
		return set;
	}

	/**
	 * Setter for the valueChar field, return a copy with the field set.
	 * @param valueChar the valueChar value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueChar(char valueChar) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueBoolean field, return a copy with the field set.
	 * @param valueBoolean the valueBoolean value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueBoolean(boolean valueBoolean) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueByte field, return a copy with the field set.
	 * @param valueByte the valueByte value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueByte(byte valueByte) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueShort field, return a copy with the field set.
	 * @param valueShort the valueShort value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueShort(short valueShort) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueInt field, return a copy with the field set.
	 * @param valueInt the valueInt value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueInt(int valueInt) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueLong field, return a copy with the field set.
	 * @param valueLong the valueLong value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueLong(long valueLong) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueFloat field, return a copy with the field set.
	 * @param valueFloat the valueFloat value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueFloat(float valueFloat) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueDouble field, return a copy with the field set.
	 * @param valueDouble the valueDouble value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueDouble(double valueDouble) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectCharacter field, return a copy with the field set.
	 * @param valueObjectCharacter the valueObjectCharacter value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectCharacter(Character valueObjectCharacter) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectBoolean field, return a copy with the field set.
	 * @param valueObjectBoolean the valueObjectBoolean value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectBoolean(Boolean valueObjectBoolean) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectByte field, return a copy with the field set.
	 * @param valueObjectByte the valueObjectByte value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectByte(Byte valueObjectByte) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectShort field, return a copy with the field set.
	 * @param valueObjectShort the valueObjectShort value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectShort(Short valueObjectShort) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectInteger field, return a copy with the field set.
	 * @param valueObjectInteger the valueObjectInteger value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectInteger(Integer valueObjectInteger) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectLong field, return a copy with the field set.
	 * @param valueObjectLong the valueObjectLong value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectLong(Long valueObjectLong) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectFloat field, return a copy with the field set.
	 * @param valueObjectFloat the valueObjectFloat value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectFloat(Float valueObjectFloat) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectDouble field, return a copy with the field set.
	 * @param valueObjectDouble the valueObjectDouble value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectDouble(Double valueObjectDouble) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectString field, return a copy with the field set.
	 * @param valueObjectString the valueObjectString value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectString(String valueObjectString) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectBytes field, return a copy with the field set.
	 * @param valueObjectBytes the valueObjectBytes value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectBytes(byte[] valueObjectBytes) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectTimeUnit field, return a copy with the field set.
	 * @param valueObjectTimeUnit the valueObjectTimeUnit value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectTimeUnit(TimeUnit valueObjectTimeUnit) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectList field, return a copy with the field set.
	 * @param valueObjectList the valueObjectList value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectList(List<String> valueObjectList) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectSet field, return a copy with the field set.
	 * @param valueObjectSet the valueObjectSet value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectSet(Set<Integer> valueObjectSet) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the valueObjectMap field, return a copy with the field set.
	 * @param valueObjectMap the valueObjectMap value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setValueObjectMap(Map<String, IMutableBean> valueObjectMap) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	/**
	 * Setter for the set field, return a copy with the field set.
	 * @param set the set value to set.
	 * @return a copy of this object.
	 */
	@Override
	public IImmutableBean setSet(boolean set) {
		return new ImmutableBean(valueChar, valueBoolean, valueByte, valueShort, valueInt, valueLong, valueFloat, valueDouble, valueObjectCharacter, valueObjectBoolean, valueObjectByte, valueObjectShort, valueObjectInteger, valueObjectLong, valueObjectFloat, valueObjectDouble, valueObjectString, valueObjectBytes, valueObjectTimeUnit, valueObjectList, valueObjectSet, valueObjectMap, set);
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(getValueChar());
		builder.append(getValueBoolean());
		builder.append(getValueByte());
		builder.append(getValueShort());
		builder.append(getValueInt());
		builder.append(getValueLong());
		builder.append(getValueFloat());
		builder.append(getValueDouble());
		builder.append(getValueObjectCharacter());
		builder.append(getValueObjectBoolean());
		builder.append(getValueObjectByte());
		builder.append(getValueObjectShort());
		builder.append(getValueObjectInteger());
		builder.append(getValueObjectLong());
		builder.append(getValueObjectFloat());
		builder.append(getValueObjectDouble());
		builder.append(getValueObjectString());
		builder.append(getValueObjectBytes());
		builder.append(getValueObjectTimeUnit());
		builder.append(getValueObjectList());
		builder.append(getValueObjectSet());
		builder.append(getValueObjectMap());
		builder.append(getSet());
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
		IImmutableBean that = (IImmutableBean) object;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.getValueChar(), that.getValueChar());
		builder.append(this.getValueBoolean(), that.getValueBoolean());
		builder.append(this.getValueByte(), that.getValueByte());
		builder.append(this.getValueShort(), that.getValueShort());
		builder.append(this.getValueInt(), that.getValueInt());
		builder.append(this.getValueLong(), that.getValueLong());
		builder.append(this.getValueFloat(), that.getValueFloat());
		builder.append(this.getValueDouble(), that.getValueDouble());
		builder.append(this.getValueObjectCharacter(), that.getValueObjectCharacter());
		builder.append(this.getValueObjectBoolean(), that.getValueObjectBoolean());
		builder.append(this.getValueObjectByte(), that.getValueObjectByte());
		builder.append(this.getValueObjectShort(), that.getValueObjectShort());
		builder.append(this.getValueObjectInteger(), that.getValueObjectInteger());
		builder.append(this.getValueObjectLong(), that.getValueObjectLong());
		builder.append(this.getValueObjectFloat(), that.getValueObjectFloat());
		builder.append(this.getValueObjectDouble(), that.getValueObjectDouble());
		builder.append(this.getValueObjectString(), that.getValueObjectString());
		builder.append(this.getValueObjectBytes(), that.getValueObjectBytes());
		builder.append(this.getValueObjectTimeUnit(), that.getValueObjectTimeUnit());
		builder.append(this.getValueObjectList(), that.getValueObjectList());
		builder.append(this.getValueObjectSet(), that.getValueObjectSet());
		builder.append(this.getValueObjectMap(), that.getValueObjectMap());
		builder.append(this.getSet(), that.getSet());
		return builder.isEquals();
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		builder.append(getValueChar());
		builder.append(getValueBoolean());
		builder.append(getValueByte());
		builder.append(getValueShort());
		builder.append(getValueInt());
		builder.append(getValueLong());
		builder.append(getValueFloat());
		builder.append(getValueDouble());
		builder.append(getValueObjectCharacter());
		builder.append(getValueObjectBoolean());
		builder.append(getValueObjectByte());
		builder.append(getValueObjectShort());
		builder.append(getValueObjectInteger());
		builder.append(getValueObjectLong());
		builder.append(getValueObjectFloat());
		builder.append(getValueObjectDouble());
		builder.append(getValueObjectString());
		builder.append(getValueObjectBytes());
		builder.append(getValueObjectTimeUnit());
		builder.append(getValueObjectList());
		builder.append(getValueObjectSet());
		builder.append(getValueObjectMap());
		builder.append(getSet());
		return builder.toString();
	}

	@Override
	public int compareTo(IImmutableBean that) {
		CompareToBuilder builder = new CompareToBuilder();
		builder.append(this.getValueChar(), that.getValueChar());
		builder.append(this.getValueBoolean(), that.getValueBoolean());
		builder.append(this.getValueByte(), that.getValueByte());
		builder.append(this.getValueShort(), that.getValueShort());
		builder.append(this.getValueInt(), that.getValueInt());
		builder.append(this.getValueLong(), that.getValueLong());
		builder.append(this.getValueFloat(), that.getValueFloat());
		builder.append(this.getValueDouble(), that.getValueDouble());
		builder.append(this.getValueObjectCharacter(), that.getValueObjectCharacter());
		builder.append(this.getValueObjectBoolean(), that.getValueObjectBoolean());
		builder.append(this.getValueObjectByte(), that.getValueObjectByte());
		builder.append(this.getValueObjectShort(), that.getValueObjectShort());
		builder.append(this.getValueObjectInteger(), that.getValueObjectInteger());
		builder.append(this.getValueObjectLong(), that.getValueObjectLong());
		builder.append(this.getValueObjectFloat(), that.getValueObjectFloat());
		builder.append(this.getValueObjectDouble(), that.getValueObjectDouble());
		builder.append(this.getValueObjectString(), that.getValueObjectString());
		builder.append(this.getValueObjectBytes(), that.getValueObjectBytes());
		builder.append(this.getValueObjectTimeUnit(), that.getValueObjectTimeUnit());
		builder.append(this.getValueObjectList(), that.getValueObjectList());
		builder.append(this.getValueObjectSet(), that.getValueObjectSet());
		builder.append(this.getValueObjectMap(), that.getValueObjectMap());
		builder.append(this.getSet(), that.getSet());
		return builder.toComparison();
	}
}
