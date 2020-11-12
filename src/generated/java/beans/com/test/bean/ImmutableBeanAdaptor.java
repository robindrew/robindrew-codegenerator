package com.test.bean;

import com.robindrew.codegenerator.api.adaptor.IAdapter;

public class ImmutableBeanAdaptor implements IAdapter<MutableBean, ImmutableBean> {

	@Override
	public ImmutableBean adapt(MutableBean from) {
		return new ImmutableBean(from.getValueChar(), from.getValueBoolean(), from.getValueByte(), from.getValueShort(), from.getValueInt(), from.getValueLong(), from.getValueFloat(), from.getValueDouble(), from.getValueObjectCharacter(), from.getValueObjectBoolean(), from.getValueObjectByte(), from.getValueObjectShort(), from.getValueObjectInteger(), from.getValueObjectLong(), from.getValueObjectFloat(), from.getValueObjectDouble(), from.getValueObjectString(), from.getValueObjectBytes(), from.getValueObjectTimeUnit(), from.getValueObjectList(), from.getValueObjectSet(), from.getValueObjectMap(), from.getSet());
	}
}
