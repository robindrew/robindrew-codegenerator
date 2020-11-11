package com.test.data;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;
import com.robindrew.codegenerator.api.sql.parser.ResultSetTypes;
import java.sql.ResultSet;

public class RowDimensionsResultSetParser implements IResultSetAdapter<IRowDimensions> {

	@Override
	public IRowDimensions adapt(ResultSet set) {
		long width = ResultSetTypes.parseLong(set, 1, "width");
		long height = ResultSetTypes.parseLong(set, 2, "height");
		IRowDimensions row = new RowDimensions(width, height);
		return row;
	}
}
