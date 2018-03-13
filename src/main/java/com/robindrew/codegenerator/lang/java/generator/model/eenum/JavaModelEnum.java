package com.robindrew.codegenerator.lang.java.generator.model.eenum;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.iinterface.JavaModelExtends;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.model.object.eenum.ModelEnum;

public class JavaModelEnum {

	private final ModelEnum eenum;
	private final List<JavaModelExtends> extendsList;
	private final List<JavaModelEnumConstant> constantList;
	private volatile IJavaType type;

	public JavaModelEnum(ModelEnum eenum, List<JavaModelExtends> extendsList, List<JavaModelEnumConstant> constantList) {
		if (eenum == null) {
			throw new NullPointerException("eenum");
		}
		if (extendsList == null) {
			throw new NullPointerException("extendsList");
		}
		if (constantList == null) {
			throw new NullPointerException("constantList");
		}
		this.eenum = eenum;
		this.extendsList = extendsList;
		this.constantList = constantList;
	}

	public ModelEnum get() {
		return eenum;
	}

	public List<JavaModelExtends> getExtendsList() {
		return extendsList;
	}

	public List<JavaModelEnumConstant> getConstantList() {
		return constantList;
	}

	public String getName() {
		return eenum.getName();
	}

	public IJavaType getType() {
		return type;
	}

	public void setType(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

}
