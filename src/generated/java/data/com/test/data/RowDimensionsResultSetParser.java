package com.test.data;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;
import com.robindrew.codegenerator.api.sql.parser.ResultSetTypes;
import java.sql.ResultSet;

public class RowDimensionsResultSetParser implements IResultSetAdapter<IRowDimensions> {

	@Override
	public IRowDimensions adapt(ResultSet set) {
		int id = ResultSetTypes.parseInt(set, 1, "id");
		long width = ResultSetTypes.parseLong(set, 2, "width");
		long height = ResultSetTypes.parseLong(set, 3, "height");
		IRowDimensions row = new RowDimensions(id, width, height);
		return row;
	}
}
