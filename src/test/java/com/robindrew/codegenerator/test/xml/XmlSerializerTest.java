package com.robindrew.codegenerator.test.xml;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.robindrew.codegenerator.api.serializer.xml.XmlReaderSerializer;
import com.robindrew.codegenerator.api.serializer.xml.XmlWriterSerializer;
import com.test.bean.IMutableBean;
import com.test.bean.MutableBean;
import com.test.bean.MutableBeanXmlSerializer;

public class XmlSerializerTest {

	@Test
	public void testSerialization() {
		MutableBean bean = new MutableBean();
		bean.setValueChar('c');
		bean.setValueDouble(2.2);
		bean.setValueLong(Long.MAX_VALUE);
		bean.setValueObjectInteger(new Integer(22));
		bean.setValueObjectList(Arrays.asList("one", "two"));
		bean.setValueObjectTimeUnit(TimeUnit.DAYS);
		bean.setValueObjectSet(Sets.newHashSet(1, 2, 3, 4, 5));

		MutableBeanXmlSerializer serializer = new MutableBeanXmlSerializer();
		String xml = new XmlWriterSerializer().toXml(serializer, bean);

		IMutableBean compare = new XmlReaderSerializer().fromXml(serializer, xml);

		Assert.assertEquals(bean, compare);
	}
}
