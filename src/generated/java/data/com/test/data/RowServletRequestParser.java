package com.test.data;

import com.robindrew.codegenerator.api.servlet.IServletRequestAdapter;
import com.robindrew.codegenerator.api.servlet.ServletRequestTypes;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletRequest;

public class RowServletRequestParser implements IServletRequestAdapter<IRow> {

	@Override
	public IRow adapt(ServletRequest request) {
		int id = ServletRequestTypes.parseInt(request, "id");
		String name = ServletRequestTypes.parseString(request, "name", true);
		byte[] data = ServletRequestTypes.parseByteArray(request, "data", true);
		long width = ServletRequestTypes.parseLong(request, "width");
		long height = ServletRequestTypes.parseLong(request, "height");
		TimeUnit unit = ServletRequestTypes.parseEnum(request, "unit", TimeUnit.class, true);
		IRow row = new Row(id, name, data, width, height, unit);
		return row;
	}
}
