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

public class MutableBean implements IMutableBean {

	/** The valueChar field. */
	private char valueChar = ' ';
	/** The valueBoolean field. */
	private boolean valueBoolean = false;
	/** The valueByte field. */
	private byte valueByte = 0;
	/** The valueShort field. */
	private short valueShort = 0;
	/** The valueInt field. */
	private int valueInt = 0;
	/** The valueLong field. */
	private long valueLong = 0l;
	/** The valueFloat field. */
	private float valueFloat = 0.0f;
	/** The valueDouble field. */
	private double valueDouble = 0.0;
	/** The valueObjectCharacter field. */
	private Character valueObjectCharacter = null;
	/** The valueObjectBoolean field. */
	private Boolean valueObjectBoolean = null;
	/** The valueObjectByte field. */
	private Byte valueObjectByte = null;
	/** The valueObjectShort field. */
	private Short valueObjectShort = null;
	/** The valueObjectInteger field. */
	private Integer valueObjectInteger = null;
	/** The valueObjectLong field. */
	private Long valueObjectLong = null;
	/** The valueObjectFloat field. */
	private Float valueObjectFloat = null;
	/** The valueObjectDouble field. */
	private Double valueObjectDouble = null;
	/** The valueObjectString field. */
	private String valueObjectString = null;
	/** The valueObjectBytes field. */
	private byte[] valueObjectBytes = null;
	/** The valueObjectTimeUnit field. */
	private TimeUnit valueObjectTimeUnit = null;
	/** The valueObjectList field. */
	private List<String> valueObjectList = null;
	/** The valueObjectSet field. */
	private Set<Integer> valueObjectSet = null;
	/** The valueObjectMap field. */
	private Map<String, IMutableBean> valueObjectMap = null;
	/** The set field. */
	private boolean set = false;

	/**
	 * The empty constructor.
	 */
	public MutableBean() {
	}

	/**
	 * The fields constructor.
	 */
	public MutableBean(char valueChar, boolean valueBoolean, byte valueByte, short valueShort, int valueInt, long valueLong, float valueFloat, double valueDouble, Character valueObjectCharacter, Boolean valueObjectBoolean, Byte valueObjectByte, Short valueObjectShort, Integer valueObjectInteger, Long valueObjectLong, Float valueObjectFloat, Double valueObjectDouble, String valueObjectString, byte[] valueObjectBytes, TimeUnit valueObjectTimeUnit, List<String> valueObjectList, Set<Integer> valueObjectSet, Map<String, IMutableBean> valueObjectMap, boolean set) {
		setValueChar(valueChar);
		setValueBoolean(valueBoolean);
		setValueByte(valueByte);
		setValueShort(valueShort);
		setValueInt(valueInt);
		setValueLong(valueLong);
		setValueFloat(valueFloat);
		setValueDouble(valueDouble);
		setValueObjectCharacter(valueObjectCharacter);
		setValueObjectBoolean(valueObjectBoolean);
		setValueObjectByte(valueObjectByte);
		setValueObjectShort(valueObjectShort);
		setValueObjectInteger(valueObjectInteger);
		setValueObjectLong(valueObjectLong);
		setValueObjectFloat(valueObjectFloat);
		setValueObjectDouble(valueObjectDouble);
		setValueObjectString(valueObjectString);
		setValueObjectBytes(valueObjectBytes);
		setValueObjectTimeUnit(valueObjectTimeUnit);
		setValueObjectList(valueObjectList);
		setValueObjectSet(valueObjectSet);
		setValueObjectMap(valueObjectMap);
		setSet(set);
	}

	/**
	 * The clone constructor.
	 */
	public MutableBean(IMutableBean clone) {
		setValueChar(clone.getValueChar());
		setValueBoolean(clone.getValueBoolean());
		setValueByte(clone.getValueByte());
		setValueShort(clone.getValueShort());
		setValueInt(clone.getValueInt());
		setValueLong(clone.getValueLong());
		setValueFloat(clone.getValueFloat());
		setValueDouble(clone.getValueDouble());
		setValueObjectCharacter(clone.getValueObjectCharacter());
		setValueObjectBoolean(clone.getValueObjectBoolean());
		setValueObjectByte(clone.getValueObjectByte());
		setValueObjectShort(clone.getValueObjectShort());
		setValueObjectInteger(clone.getValueObjectInteger());
		setValueObjectLong(clone.getValueObjectLong());
		setValueObjectFloat(clone.getValueObjectFloat());
		setValueObjectDouble(clone.getValueObjectDouble());
		setValueObjectString(clone.getValueObjectString());
		setValueObjectBytes(clone.getValueObjectBytes());
		setValueObjectTimeUnit(clone.getValueObjectTimeUnit());
		setValueObjectList(clone.getValueObjectList());
		setValueObjectSet(clone.getValueObjectSet());
		setValueObjectMap(clone.getValueObjectMap());
		setSet(clone.getSet());
	}

	/**
	 * The clone constructor.
	 */
	public MutableBean(IImmutableBean clone) {
		setValueChar(clone.getValueChar());
		setValueBoolean(clone.getValueBoolean());
		setValueByte(clone.getValueByte());
		setValueShort(clone.getValueShort());
		setValueInt(clone.getValueInt());
		setValueLong(clone.getValueLong());
		setValueFloat(clone.getValueFloat());
		setValueDouble(clone.getValueDouble());
		setValueObjectCharacter(clone.getValueObjectCharacter());
		setValueObjectBoolean(clone.getValueObjectBoolean());
		setValueObjectByte(clone.getValueObjectByte());
		setValueObjectShort(clone.getValueObjectShort());
		setValueObjectInteger(clone.getValueObjectInteger());
		setValueObjectLong(clone.getValueObjectLong());
		setValueObjectFloat(clone.getValueObjectFloat());
		setValueObjectDouble(clone.getValueObjectDouble());
		setValueObjectString(clone.getValueObjectString());
		setValueObjectBytes(clone.getValueObjectBytes());
		setValueObjectTimeUnit(clone.getValueObjectTimeUnit());
		setValueObjectList(clone.getValueObjectList());
		setValueObjectSet(clone.getValueObjectSet());
		setValueObjectMap(clone.getValueObjectMap());
		setSet(clone.getSet());
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
	 * Setter for the valueChar field.
	 * @param valueChar the valueChar value to set.
	 */
	@Override
	public void setValueChar(char valueChar) {
		this.valueChar = valueChar;
	}

	/**
	 * Setter for the valueBoolean field.
	 * @param valueBoolean the valueBoolean value to set.
	 */
	@Override
	public void setValueBoolean(boolean valueBoolean) {
		this.valueBoolean = valueBoolean;
	}

	/**
	 * Setter for the valueByte field.
	 * @param valueByte the valueByte value to set.
	 */
	@Override
	public void setValueByte(byte valueByte) {
		this.valueByte = valueByte;
	}

	/**
	 * Setter for the valueShort field.
	 * @param valueShort the valueShort value to set.
	 */
	@Override
	public void setValueShort(short valueShort) {
		this.valueShort = valueShort;
	}

	/**
	 * Setter for the valueInt field.
	 * @param valueInt the valueInt value to set.
	 */
	@Override
	public void setValueInt(int valueInt) {
		this.valueInt = valueInt;
	}

	/**
	 * Setter for the valueLong field.
	 * @param valueLong the valueLong value to set.
	 */
	@Override
	public void setValueLong(long valueLong) {
		this.valueLong = valueLong;
	}

	/**
	 * Setter for the valueFloat field.
	 * @param valueFloat the valueFloat value to set.
	 */
	@Override
	public void setValueFloat(float valueFloat) {
		this.valueFloat = valueFloat;
	}

	/**
	 * Setter for the valueDouble field.
	 * @param valueDouble the valueDouble value to set.
	 */
	@Override
	public void setValueDouble(double valueDouble) {
		this.valueDouble = valueDouble;
	}

	/**
	 * Setter for the valueObjectCharacter field.
	 * @param valueObjectCharacter the valueObjectCharacter value to set.
	 */
	@Override
	public void setValueObjectCharacter(Character valueObjectCharacter) {
		this.valueObjectCharacter = valueObjectCharacter;
	}

	/**
	 * Setter for the valueObjectBoolean field.
	 * @param valueObjectBoolean the valueObjectBoolean value to set.
	 */
	@Override
	public void setValueObjectBoolean(Boolean valueObjectBoolean) {
		this.valueObjectBoolean = valueObjectBoolean;
	}

	/**
	 * Setter for the valueObjectByte field.
	 * @param valueObjectByte the valueObjectByte value to set.
	 */
	@Override
	public void setValueObjectByte(Byte valueObjectByte) {
		this.valueObjectByte = valueObjectByte;
	}

	/**
	 * Setter for the valueObjectShort field.
	 * @param valueObjectShort the valueObjectShort value to set.
	 */
	@Override
	public void setValueObjectShort(Short valueObjectShort) {
		this.valueObjectShort = valueObjectShort;
	}

	/**
	 * Setter for the valueObjectInteger field.
	 * @param valueObjectInteger the valueObjectInteger value to set.
	 */
	@Override
	public void setValueObjectInteger(Integer valueObjectInteger) {
		this.valueObjectInteger = valueObjectInteger;
	}

	/**
	 * Setter for the valueObjectLong field.
	 * @param valueObjectLong the valueObjectLong value to set.
	 */
	@Override
	public void setValueObjectLong(Long valueObjectLong) {
		this.valueObjectLong = valueObjectLong;
	}

	/**
	 * Setter for the valueObjectFloat field.
	 * @param valueObjectFloat the valueObjectFloat value to set.
	 */
	@Override
	public void setValueObjectFloat(Float valueObjectFloat) {
		this.valueObjectFloat = valueObjectFloat;
	}

	/**
	 * Setter for the valueObjectDouble field.
	 * @param valueObjectDouble the valueObjectDouble value to set.
	 */
	@Override
	public void setValueObjectDouble(Double valueObjectDouble) {
		this.valueObjectDouble = valueObjectDouble;
	}

	/**
	 * Setter for the valueObjectString field.
	 * @param valueObjectString the valueObjectString value to set.
	 */
	@Override
	public void setValueObjectString(String valueObjectString) {
		this.valueObjectString = valueObjectString;
	}

	/**
	 * Setter for the valueObjectBytes field.
	 * @param valueObjectBytes the valueObjectBytes value to set.
	 */
	@Override
	public void setValueObjectBytes(byte[] valueObjectBytes) {
		this.valueObjectBytes = valueObjectBytes;
	}

	/**
	 * Setter for the valueObjectTimeUnit field.
	 * @param valueObjectTimeUnit the valueObjectTimeUnit value to set.
	 */
	@Override
	public void setValueObjectTimeUnit(TimeUnit valueObjectTimeUnit) {
		this.valueObjectTimeUnit = valueObjectTimeUnit;
	}

	/**
	 * Setter for the valueObjectList field.
	 * @param valueObjectList the valueObjectList value to set.
	 */
	@Override
	public void setValueObjectList(List<String> valueObjectList) {
		this.valueObjectList = valueObjectList;
	}

	/**
	 * Setter for the valueObjectSet field.
	 * @param valueObjectSet the valueObjectSet value to set.
	 */
	@Override
	public void setValueObjectSet(Set<Integer> valueObjectSet) {
		this.valueObjectSet = valueObjectSet;
	}

	/**
	 * Setter for the valueObjectMap field.
	 * @param valueObjectMap the valueObjectMap value to set.
	 */
	@Override
	public void setValueObjectMap(Map<String, IMutableBean> valueObjectMap) {
		this.valueObjectMap = valueObjectMap;
	}

	/**
	 * Setter for the set field.
	 * @param set the set value to set.
	 */
	@Override
	public void setSet(boolean set) {
		this.set = set;
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
		IMutableBean that = (IMutableBean) object;
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
	public int compareTo(IMutableBean that) {
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
