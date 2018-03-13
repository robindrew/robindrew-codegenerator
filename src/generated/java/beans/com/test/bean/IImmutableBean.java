package com.test.bean;

import com.robindrew.codegenerator.api.bean.IBean;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface IImmutableBean extends IBean, IValueBean, Comparable<IImmutableBean> {

	int SERIALIZATION_ID = 1002;

	/**
	 * Returns the serialization id.
	 * @return the serialization id.
	 */
	int getSerializationId();

	/**
	 * Getter for the valueChar field.
	 * @return the value of the valueChar field.
	 */
	char getValueChar();

	/**
	 * Getter for the valueBoolean field.
	 * @return the value of the valueBoolean field.
	 */
	boolean getValueBoolean();

	/**
	 * Getter for the valueByte field.
	 * @return the value of the valueByte field.
	 */
	byte getValueByte();

	/**
	 * Getter for the valueShort field.
	 * @return the value of the valueShort field.
	 */
	short getValueShort();

	/**
	 * Getter for the valueInt field.
	 * @return the value of the valueInt field.
	 */
	int getValueInt();

	/**
	 * Getter for the valueLong field.
	 * @return the value of the valueLong field.
	 */
	long getValueLong();

	/**
	 * Getter for the valueFloat field.
	 * @return the value of the valueFloat field.
	 */
	float getValueFloat();

	/**
	 * Getter for the valueDouble field.
	 * @return the value of the valueDouble field.
	 */
	double getValueDouble();

	/**
	 * Getter for the valueObjectCharacter field.
	 * @return the value of the valueObjectCharacter field.
	 */
	Character getValueObjectCharacter();

	/**
	 * Getter for the valueObjectBoolean field.
	 * @return the value of the valueObjectBoolean field.
	 */
	Boolean getValueObjectBoolean();

	/**
	 * Getter for the valueObjectByte field.
	 * @return the value of the valueObjectByte field.
	 */
	Byte getValueObjectByte();

	/**
	 * Getter for the valueObjectShort field.
	 * @return the value of the valueObjectShort field.
	 */
	Short getValueObjectShort();

	/**
	 * Getter for the valueObjectInteger field.
	 * @return the value of the valueObjectInteger field.
	 */
	Integer getValueObjectInteger();

	/**
	 * Getter for the valueObjectLong field.
	 * @return the value of the valueObjectLong field.
	 */
	Long getValueObjectLong();

	/**
	 * Getter for the valueObjectFloat field.
	 * @return the value of the valueObjectFloat field.
	 */
	Float getValueObjectFloat();

	/**
	 * Getter for the valueObjectDouble field.
	 * @return the value of the valueObjectDouble field.
	 */
	double getValueObjectDouble();

	/**
	 * Getter for the valueObjectString field.
	 * @return the value of the valueObjectString field.
	 */
	String getValueObjectString();

	/**
	 * Getter for the valueObjectBytes field.
	 * @return the value of the valueObjectBytes field.
	 */
	byte[] getValueObjectBytes();

	/**
	 * Getter for the valueObjectTimeUnit field.
	 * @return the value of the valueObjectTimeUnit field.
	 */
	TimeUnit getValueObjectTimeUnit();

	/**
	 * Getter for the valueObjectList field.
	 * @return the value of the valueObjectList field.
	 */
	List<String> getValueObjectList();

	/**
	 * Getter for the valueObjectSet field.
	 * @return the value of the valueObjectSet field.
	 */
	Set<Integer> getValueObjectSet();

	/**
	 * Getter for the valueObjectMap field.
	 * @return the value of the valueObjectMap field.
	 */
	Map<String, IMutableBean> getValueObjectMap();

	/**
	 * Getter for the set field.
	 * @return the value of the set field.
	 */
	boolean getSet();

	/**
	 * Setter for the valueChar field, return a copy with the field set.
	 * @param valueChar the valueChar value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueChar(char valueChar);

	/**
	 * Setter for the valueBoolean field, return a copy with the field set.
	 * @param valueBoolean the valueBoolean value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueBoolean(boolean valueBoolean);

	/**
	 * Setter for the valueByte field, return a copy with the field set.
	 * @param valueByte the valueByte value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueByte(byte valueByte);

	/**
	 * Setter for the valueShort field, return a copy with the field set.
	 * @param valueShort the valueShort value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueShort(short valueShort);

	/**
	 * Setter for the valueInt field, return a copy with the field set.
	 * @param valueInt the valueInt value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueInt(int valueInt);

	/**
	 * Setter for the valueLong field, return a copy with the field set.
	 * @param valueLong the valueLong value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueLong(long valueLong);

	/**
	 * Setter for the valueFloat field, return a copy with the field set.
	 * @param valueFloat the valueFloat value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueFloat(float valueFloat);

	/**
	 * Setter for the valueDouble field, return a copy with the field set.
	 * @param valueDouble the valueDouble value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueDouble(double valueDouble);

	/**
	 * Setter for the valueObjectCharacter field, return a copy with the field set.
	 * @param valueObjectCharacter the valueObjectCharacter value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectCharacter(Character valueObjectCharacter);

	/**
	 * Setter for the valueObjectBoolean field, return a copy with the field set.
	 * @param valueObjectBoolean the valueObjectBoolean value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectBoolean(Boolean valueObjectBoolean);

	/**
	 * Setter for the valueObjectByte field, return a copy with the field set.
	 * @param valueObjectByte the valueObjectByte value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectByte(Byte valueObjectByte);

	/**
	 * Setter for the valueObjectShort field, return a copy with the field set.
	 * @param valueObjectShort the valueObjectShort value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectShort(Short valueObjectShort);

	/**
	 * Setter for the valueObjectInteger field, return a copy with the field set.
	 * @param valueObjectInteger the valueObjectInteger value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectInteger(Integer valueObjectInteger);

	/**
	 * Setter for the valueObjectLong field, return a copy with the field set.
	 * @param valueObjectLong the valueObjectLong value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectLong(Long valueObjectLong);

	/**
	 * Setter for the valueObjectFloat field, return a copy with the field set.
	 * @param valueObjectFloat the valueObjectFloat value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectFloat(Float valueObjectFloat);

	/**
	 * Setter for the valueObjectDouble field, return a copy with the field set.
	 * @param valueObjectDouble the valueObjectDouble value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectDouble(double valueObjectDouble);

	/**
	 * Setter for the valueObjectString field, return a copy with the field set.
	 * @param valueObjectString the valueObjectString value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectString(String valueObjectString);

	/**
	 * Setter for the valueObjectBytes field, return a copy with the field set.
	 * @param valueObjectBytes the valueObjectBytes value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectBytes(byte[] valueObjectBytes);

	/**
	 * Setter for the valueObjectTimeUnit field, return a copy with the field set.
	 * @param valueObjectTimeUnit the valueObjectTimeUnit value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectTimeUnit(TimeUnit valueObjectTimeUnit);

	/**
	 * Setter for the valueObjectList field, return a copy with the field set.
	 * @param valueObjectList the valueObjectList value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectList(List<String> valueObjectList);

	/**
	 * Setter for the valueObjectSet field, return a copy with the field set.
	 * @param valueObjectSet the valueObjectSet value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectSet(Set<Integer> valueObjectSet);

	/**
	 * Setter for the valueObjectMap field, return a copy with the field set.
	 * @param valueObjectMap the valueObjectMap value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setValueObjectMap(Map<String, IMutableBean> valueObjectMap);

	/**
	 * Setter for the set field, return a copy with the field set.
	 * @param set the set value to set.
	 * @return a copy of this object.
	 */
	IImmutableBean setSet(boolean set);
}
