package com.test.data;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.array.ByteArraySerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.EnumSerializer;
import com.robindrew.codegenerator.api.serializer.data.serializer.lang.StringSerializer;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RowDataSerializer extends ObjectSerializer<IRow> {

	public RowDataSerializer() {
		super(false);
	}

	public RowDataSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public IRow readValue(IDataReader reader) throws IOException {
		int param1 = reader.readInt();
		String param2 = reader.readObject(new StringSerializer(false));
		byte[] param3 = reader.readObject(new ByteArraySerializer(false));
		long param4 = reader.readLong();
		long param5 = reader.readLong();
		TimeUnit param6 = reader.readObject(new EnumSerializer<TimeUnit>(TimeUnit.class, false));
		return new Row(param1, param2, param3, param4, param5, param6);
	}

	@Override
	public void writeValue(IDataWriter writer, IRow object) throws IOException {
		writer.writeInt(object.getId());
		writer.writeObject(object.getName(), new StringSerializer(false));
		writer.writeObject(object.getData(), new ByteArraySerializer(false));
		writer.writeLong(object.getWidth());
		writer.writeLong(object.getHeight());
		writer.writeObject(object.getUnit(), new EnumSerializer<TimeUnit>(TimeUnit.class, false));
	}
}
