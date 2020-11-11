package com.test.data;

import com.robindrew.common.io.data.IDataReader;
import com.robindrew.common.io.data.IDataWriter;
import com.robindrew.common.io.data.serializer.ObjectSerializer;
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
		long param1 = reader.readLong();
		long param2 = reader.readLong();
		return new RowDimensions(param1, param2);
	}

	@Override
	public void writeValue(IDataWriter writer, IRowDimensions object) throws IOException {
		writer.writeLong(object.getWidth());
		writer.writeLong(object.getHeight());
	}
}
