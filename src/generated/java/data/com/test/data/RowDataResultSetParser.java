package com.test.data;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;
import com.robindrew.codegenerator.api.sql.parser.ResultSetTypes;
import java.sql.ResultSet;

public class RowDataResultSetParser implements IResultSetAdapter<IRowData> {

	@Override
	public IRowData adapt(ResultSet set) {
		byte[] data = ResultSetTypes.parseByteArray(set, 1, "data", true);
		IRowData row = new RowData(data);
		return row;
	}
}
