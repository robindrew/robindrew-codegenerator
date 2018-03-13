package com.test.bean;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.array.ByteArraySerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.collection.ListSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.collection.MapSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.collection.SetSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.BooleanSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.ByteSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.CharacterSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.EnumSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.FloatSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.IntegerSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.LongSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.ShortSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.StringSerializer;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MutableBeanDataSerializer extends ObjectSerializer<IMutableBean> {

	public MutableBeanDataSerializer() {
		super(false);
	}

	public MutableBeanDataSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public IMutableBean readValue(IDataReader reader) throws IOException {
		char param1 = reader.readChar();
		boolean param2 = reader.readBoolean();
		byte param3 = reader.readByte();
		short param4 = reader.readShort();
		int param5 = reader.readInt();
		long param6 = reader.readLong();
		float param7 = reader.readFloat();
		double param8 = reader.readDouble();
		Character param9 = reader.readObject(new CharacterSerializer(false));
		Boolean param10 = reader.readObject(new BooleanSerializer(false));
		Byte param11 = reader.readObject(new ByteSerializer(false));
		Short param12 = reader.readObject(new ShortSerializer(false));
		Integer param13 = reader.readObject(new IntegerSerializer(false));
		Long param14 = reader.readObject(new LongSerializer(false));
		Float param15 = reader.readObject(new FloatSerializer(false));
		double param16 = reader.readDouble();
		String param17 = reader.readObject(new StringSerializer(false));
		byte[] param18 = reader.readObject(new ByteArraySerializer(false));
		TimeUnit param19 = reader.readObject(new EnumSerializer<TimeUnit>(TimeUnit.class, false));
		List<String> param20 = reader.readObject(new ListSerializer<String>(new StringSerializer(false), false));
		Set<Integer> param21 = reader.readObject(new SetSerializer<Integer>(new IntegerSerializer(false), false));
		Map<String, IMutableBean> param22 = reader.readObject(new MapSerializer<String, IMutableBean>(new StringSerializer(false), new MutableBeanDataSerializer(false), false));
		boolean param23 = reader.readBoolean();
		return new MutableBean(param1, param2, param3, param4, param5, param6, param7, param8, param9, param10, param11, param12, param13, param14, param15, param16, param17, param18, param19, param20, param21, param22, param23);
	}

	@Override
	public void writeValue(IDataWriter writer, IMutableBean object) throws IOException {
		writer.writeChar(object.getValueChar());
		writer.writeBoolean(object.getValueBoolean());
		writer.writeByte(object.getValueByte());
		writer.writeShort(object.getValueShort());
		writer.writeInt(object.getValueInt());
		writer.writeLong(object.getValueLong());
		writer.writeFloat(object.getValueFloat());
		writer.writeDouble(object.getValueDouble());
		writer.writeObject(object.getValueObjectCharacter(), new CharacterSerializer(false));
		writer.writeObject(object.getValueObjectBoolean(), new BooleanSerializer(false));
		writer.writeObject(object.getValueObjectByte(), new ByteSerializer(false));
		writer.writeObject(object.getValueObjectShort(), new ShortSerializer(false));
		writer.writeObject(object.getValueObjectInteger(), new IntegerSerializer(false));
		writer.writeObject(object.getValueObjectLong(), new LongSerializer(false));
		writer.writeObject(object.getValueObjectFloat(), new FloatSerializer(false));
		writer.writeDouble(object.getValueObjectDouble());
		writer.writeObject(object.getValueObjectString(), new StringSerializer(false));
		writer.writeObject(object.getValueObjectBytes(), new ByteArraySerializer(false));
		writer.writeObject(object.getValueObjectTimeUnit(), new EnumSerializer<TimeUnit>(TimeUnit.class, false));
		writer.writeObject(object.getValueObjectList(), new ListSerializer<String>(new StringSerializer(false), false));
		writer.writeObject(object.getValueObjectSet(), new SetSerializer<Integer>(new IntegerSerializer(false), false));
		writer.writeObject(object.getValueObjectMap(), new MapSerializer<String, IMutableBean>(new StringSerializer(false), new MutableBeanDataSerializer(false), false));
		writer.writeBoolean(object.getSet());
	}
}
