package com.test.bean;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlWriter;
import com.robindrew.codegenerator.api.serializer.xml.serializer.array.ByteArraySerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.collection.ListSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.collection.MapSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.collection.SetSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.BooleanSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.ByteSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.CharacterSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.EnumSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.FloatSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.IntegerSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.LongSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.ShortSerializer;
import com.robindrew.codegenerator.api.serializer.xml.serializer.lang.StringSerializer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MutableBeanXmlSerializer implements IXmlSerializer<IMutableBean> {

	private String name;

	public MutableBeanXmlSerializer() {
		this("MutableBean");
	}

	public MutableBeanXmlSerializer(String name) {
		this.name = name;
	}

	/**
	 * Getter for the name field.
	 * @return the value of the name field.
	 */
	public String getName() {
		return name;
	}

	@Override
	public IMutableBean readObject(IXmlReader reader) {
		reader.startElement(getName());
		char param1 = reader.readChar("valueChar");
		boolean param2 = reader.readBoolean("valueBoolean");
		byte param3 = reader.readByte("valueByte");
		short param4 = reader.readShort("valueShort");
		int param5 = reader.readInt("valueInt");
		long param6 = reader.readLong("valueLong");
		float param7 = reader.readFloat("valueFloat");
		double param8 = reader.readDouble("valueDouble");
		Character param9 = reader.readObject(new CharacterSerializer("valueObjectCharacter"));
		Boolean param10 = reader.readObject(new BooleanSerializer("valueObjectBoolean"));
		Byte param11 = reader.readObject(new ByteSerializer("valueObjectByte"));
		Short param12 = reader.readObject(new ShortSerializer("valueObjectShort"));
		Integer param13 = reader.readObject(new IntegerSerializer("valueObjectInteger"));
		Long param14 = reader.readObject(new LongSerializer("valueObjectLong"));
		Float param15 = reader.readObject(new FloatSerializer("valueObjectFloat"));
		double param16 = reader.readDouble("valueObjectDouble");
		String param17 = reader.readObject(new StringSerializer("valueObjectString"));
		byte[] param18 = reader.readObject(new ByteArraySerializer("valueObjectBytes"));
		TimeUnit param19 = reader.readObject(new EnumSerializer<TimeUnit>(TimeUnit.class, "valueObjectTimeUnit"));
		List<String> param20 = reader.readObject(new ListSerializer<String>("valueObjectList", new StringSerializer("element")));
		Set<Integer> param21 = reader.readObject(new SetSerializer<Integer>("valueObjectSet", new IntegerSerializer("element")));
		Map<String, IMutableBean> param22 = reader.readObject(new MapSerializer<String, IMutableBean>("valueObjectMap", new StringSerializer("key"), new MutableBeanXmlSerializer("value")));
		boolean param23 = reader.readBoolean("set");
		reader.endElement(getName());

		// Create the bean
		return new MutableBean(param1, param2, param3, param4, param5, param6, param7, param8, param9, param10, param11, param12, param13, param14, param15, param16, param17, param18, param19, param20, param21, param22, param23);
	}

	@Override
	public void writeObject(IXmlWriter writer, IMutableBean object) {
		writer.startElement(getName());
		writer.writeChar("valueChar", object.getValueChar());
		writer.writeBoolean("valueBoolean", object.getValueBoolean());
		writer.writeByte("valueByte", object.getValueByte());
		writer.writeShort("valueShort", object.getValueShort());
		writer.writeInt("valueInt", object.getValueInt());
		writer.writeLong("valueLong", object.getValueLong());
		writer.writeFloat("valueFloat", object.getValueFloat());
		writer.writeDouble("valueDouble", object.getValueDouble());
		writer.writeObject(object.getValueObjectCharacter(), new CharacterSerializer("valueObjectCharacter"));
		writer.writeObject(object.getValueObjectBoolean(), new BooleanSerializer("valueObjectBoolean"));
		writer.writeObject(object.getValueObjectByte(), new ByteSerializer("valueObjectByte"));
		writer.writeObject(object.getValueObjectShort(), new ShortSerializer("valueObjectShort"));
		writer.writeObject(object.getValueObjectInteger(), new IntegerSerializer("valueObjectInteger"));
		writer.writeObject(object.getValueObjectLong(), new LongSerializer("valueObjectLong"));
		writer.writeObject(object.getValueObjectFloat(), new FloatSerializer("valueObjectFloat"));
		writer.writeDouble("valueObjectDouble", object.getValueObjectDouble());
		writer.writeObject(object.getValueObjectString(), new StringSerializer("valueObjectString"));
		writer.writeObject(object.getValueObjectBytes(), new ByteArraySerializer("valueObjectBytes"));
		writer.writeObject(object.getValueObjectTimeUnit(), new EnumSerializer<TimeUnit>(TimeUnit.class, "valueObjectTimeUnit"));
		writer.writeObject(object.getValueObjectList(), new ListSerializer<String>("valueObjectList", new StringSerializer("element")));
		writer.writeObject(object.getValueObjectSet(), new SetSerializer<Integer>("valueObjectSet", new IntegerSerializer("element")));
		writer.writeObject(object.getValueObjectMap(), new MapSerializer<String, IMutableBean>("valueObjectMap", new StringSerializer("key"), new MutableBeanXmlSerializer("value")));
		writer.writeBoolean("set", object.getSet());
		writer.endElement(getName());
	}
}
