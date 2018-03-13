package com.test.data;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;
import java.io.IOException;

public class RowDimensionsDataSerializer extends ObjectSerializer<IRowDimensions> {

	public RowDimensionsDataSerializer() {
		super(false);
	}

	public RowDimensionsDataSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public IRowDimensions readValue(IDataReader reader) throws IOException {
		int param1 = reader.readInt();
		long param2 = reader.readLong();
		long param3 = reader.readLong();
		return new RowDimensions(param1, param2, param3);
	}

	@Override
	public void writeValue(IDataWriter writer, IRowDimensions object) throws IOException {
		writer.writeInt(object.getId());
		writer.writeLong(object.getWidth());
		writer.writeLong(object.getHeight());
	}
}
