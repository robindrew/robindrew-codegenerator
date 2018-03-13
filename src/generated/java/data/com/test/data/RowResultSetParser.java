package com.test.data;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;
import com.robindrew.codegenerator.api.sql.parser.ResultSetTypes;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

public class RowResultSetParser implements IResultSetAdapter<IRow> {

	@Override
	public IRow adapt(ResultSet set) {
		int id = ResultSetTypes.parseInt(set, 1, "id");
		String name = ResultSetTypes.parseString(set, 2, "name", true);
		byte[] data = ResultSetTypes.parseByteArray(set, 3, "data", true);
		long width = ResultSetTypes.parseLong(set, 4, "width");
		long height = ResultSetTypes.parseLong(set, 5, "height");
		TimeUnit unit = ResultSetTypes.parseEnum(set, 6, "unit", TimeUnit.class, true);
		IRow row = new Row(id, name, data, width, height, unit);
		return row;
	}
}
