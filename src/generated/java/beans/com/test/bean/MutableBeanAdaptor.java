package com.test.bean;

import com.robindrew.codegenerator.api.adaptor.IAdapter;

public class MutableBeanAdaptor implements IAdapter<ImmutableBean, MutableBean> {

	@Override
	public MutableBean adapt(ImmutableBean from) {
		MutableBean to = new MutableBean();
		to.setValueChar(from.getValueChar());
		to.setValueBoolean(from.getValueBoolean());
		to.setValueByte(from.getValueByte());
		to.setValueShort(from.getValueShort());
		to.setValueInt(from.getValueInt());
		to.setValueLong(from.getValueLong());
		to.setValueFloat(from.getValueFloat());
		to.setValueDouble(from.getValueDouble());
		to.setValueObjectCharacter(from.getValueObjectCharacter());
		to.setValueObjectBoolean(from.getValueObjectBoolean());
		to.setValueObjectByte(from.getValueObjectByte());
		to.setValueObjectShort(from.getValueObjectShort());
		to.setValueObjectInteger(from.getValueObjectInteger());
		to.setValueObjectLong(from.getValueObjectLong());
		to.setValueObjectFloat(from.getValueObjectFloat());
		to.setValueObjectDouble(from.getValueObjectDouble());
		to.setValueObjectString(from.getValueObjectString());
		to.setValueObjectBytes(from.getValueObjectBytes());
		to.setValueObjectTimeUnit(from.getValueObjectTimeUnit());
		return to;
	}
}
