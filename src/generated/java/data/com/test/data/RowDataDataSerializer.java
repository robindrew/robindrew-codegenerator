package com.test.data;

import com.robindrew.common.io.data.IDataReader;
import com.robindrew.common.io.data.IDataWriter;
import com.robindrew.common.io.data.serializer.ObjectSerializer;
import com.robindrew.common.io.data.serializer.array.ByteArraySerializer;
import java.io.IOException;

public class RowDataDataSerializer extends ObjectSerializer<IRowData> {

	public RowDataDataSerializer() {
		super(false);
	}

	public RowDataDataSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public IRowData readValue(IDataReader reader) throws IOException {
		byte[] param1 = reader.readObject(new ByteArraySerializer(false));
		return new RowData(param1);
	}

	@Override
	public void writeValue(IDataWriter writer, IRowData object) throws IOException {
		writer.writeObject(object.getData(), new ByteArraySerializer(false));
	}
}
