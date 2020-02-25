package com.test.bean;

import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface IMutableBean extends IExecutableBean<List<Integer>>, IValueBean, Comparable<IMutableBean> {

	int SERIALIZATION_ID = 1001;

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
	 * Setter for the valueChar field.
	 * @param valueChar the valueChar value to set.
	 */
	void setValueChar(char valueChar);

	/**
	 * Setter for the valueBoolean field.
	 * @param valueBoolean the valueBoolean value to set.
	 */
	void setValueBoolean(boolean valueBoolean);

	/**
	 * Setter for the valueByte field.
	 * @param valueByte the valueByte value to set.
	 */
	void setValueByte(byte valueByte);

	/**
	 * Setter for the valueShort field.
	 * @param valueShort the valueShort value to set.
	 */
	void setValueShort(short valueShort);

	/**
	 * Setter for the valueInt field.
	 * @param valueInt the valueInt value to set.
	 */
	void setValueInt(int valueInt);

	/**
	 * Setter for the valueLong field.
	 * @param valueLong the valueLong value to set.
	 */
	void setValueLong(long valueLong);

	/**
	 * Setter for the valueFloat field.
	 * @param valueFloat the valueFloat value to set.
	 */
	void setValueFloat(float valueFloat);

	/**
	 * Setter for the valueDouble field.
	 * @param valueDouble the valueDouble value to set.
	 */
	void setValueDouble(double valueDouble);

	/**
	 * Setter for the valueObjectCharacter field.
	 * @param valueObjectCharacter the valueObjectCharacter value to set.
	 */
	void setValueObjectCharacter(Character valueObjectCharacter);

	/**
	 * Setter for the valueObjectBoolean field.
	 * @param valueObjectBoolean the valueObjectBoolean value to set.
	 */
	void setValueObjectBoolean(Boolean valueObjectBoolean);

	/**
	 * Setter for the valueObjectByte field.
	 * @param valueObjectByte the valueObjectByte value to set.
	 */
	void setValueObjectByte(Byte valueObjectByte);

	/**
	 * Setter for the valueObjectShort field.
	 * @param valueObjectShort the valueObjectShort value to set.
	 */
	void setValueObjectShort(Short valueObjectShort);

	/**
	 * Setter for the valueObjectInteger field.
	 * @param valueObjectInteger the valueObjectInteger value to set.
	 */
	void setValueObjectInteger(Integer valueObjectInteger);

	/**
	 * Setter for the valueObjectLong field.
	 * @param valueObjectLong the valueObjectLong value to set.
	 */
	void setValueObjectLong(Long valueObjectLong);

	/**
	 * Setter for the valueObjectFloat field.
	 * @param valueObjectFloat the valueObjectFloat value to set.
	 */
	void setValueObjectFloat(Float valueObjectFloat);

	/**
	 * Setter for the valueObjectDouble field.
	 * @param valueObjectDouble the valueObjectDouble value to set.
	 */
	void setValueObjectDouble(double valueObjectDouble);

	/**
	 * Setter for the valueObjectString field.
	 * @param valueObjectString the valueObjectString value to set.
	 */
	void setValueObjectString(String valueObjectString);

	/**
	 * Setter for the valueObjectBytes field.
	 * @param valueObjectBytes the valueObjectBytes value to set.
	 */
	void setValueObjectBytes(byte[] valueObjectBytes);

	/**
	 * Setter for the valueObjectTimeUnit field.
	 * @param valueObjectTimeUnit the valueObjectTimeUnit value to set.
	 */
	void setValueObjectTimeUnit(TimeUnit valueObjectTimeUnit);

	/**
	 * Setter for the valueObjectList field.
	 * @param valueObjectList the valueObjectList value to set.
	 */
	void setValueObjectList(List<String> valueObjectList);

	/**
	 * Setter for the valueObjectSet field.
	 * @param valueObjectSet the valueObjectSet value to set.
	 */
	void setValueObjectSet(Set<Integer> valueObjectSet);

	/**
	 * Setter for the valueObjectMap field.
	 * @param valueObjectMap the valueObjectMap value to set.
	 */
	void setValueObjectMap(Map<String, IMutableBean> valueObjectMap);

	/**
	 * Setter for the set field.
	 * @param set the set value to set.
	 */
	void setSet(boolean set);
}
