package com.robindrew.codegenerator.lang.java.type.block.method.adapt;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.JavaMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;

public class AdaptMethod extends JavaMethod {

	private final IJavaType toType;
	private final IJavaType toInstanceType;
	private final Map<IJavaName, IJavaName> fieldMap;

	public AdaptMethod(IJavaType fromType, IJavaType toType, IJavaType toInstanceType, Map<IJavaName, IJavaName> fieldMap, boolean constructor) {
		super("adapt", toType);
		if (fieldMap.isEmpty()) {
			throw new IllegalArgumentException("fieldMap is empty");
		}
		if (fromType == null) {
			throw new NullPointerException("fromType");
		}
		if (toType == null) {
			throw new NullPointerException("toType");
		}
		if (toInstanceType == null) {
			throw new NullPointerException("toInstanceType");
		}

		this.toType = toType;
		this.toInstanceType = toInstanceType;
		this.fieldMap = fieldMap;

		// Parameters
		getParameters().add("from", fromType);

		// References
		getReferences().add(fromType);
		getReferences().add(toType);
		getReferences().add(toInstanceType);

		// Method contents!
		if (constructor) {
			setContents(adaptConstructor());
		} else {
			setContents(adaptFields());
		}
	}

	private IJavaCodeBlock adaptConstructor() {
		String toInstanceName = toInstanceType.getSimpleName();

		// Slightly nasty way to build an object if the constructor is large
		// but what can you do? This is intended for objects that have no setters
		boolean comma = false;
		StringBuilder parameters = new StringBuilder();
		for (Entry<IJavaName, IJavaName> entry : fieldMap.entrySet()) {
			if (comma) {
				parameters.append(", ");
			}
			comma = true;
			IJavaName from = entry.getKey();
			parameters.append("from." + toGetter(from) + "()");
		}
		JavaCodeLines code = new JavaCodeLines();
		code.line("return new " + toInstanceName + "(" + parameters + ");");
		return code;
	}

	private IJavaCodeBlock adaptFields() {
		String toName = toType.getSimpleName();
		String toInstanceName = toInstanceType.getSimpleName();

		JavaCodeLines code = new JavaCodeLines();
		code.line(toName + " to = new " + toInstanceName + "();");
		for (Entry<IJavaName, IJavaName> entry : fieldMap.entrySet()) {
			IJavaName from = entry.getKey();
			IJavaName to = entry.getValue();
			code.line("to." + toSetter(to) + "(from." + toGetter(from) + "());");
		}
		code.line("return to;");
		return code;
	}

	private String toGetter(IJavaName name) {
		StringBuilder setter = new StringBuilder();
		List<IJavaName> nameList = name.split();
		int setIndex = nameList.size() - 1;
		for (int getIndex = 0; getIndex < setIndex; getIndex++) {
			setter.append("get");
			setter.append(nameList.get(getIndex).toUpper());
			setter.append("().");
		}
		setter.append("get");
		setter.append(nameList.get(setIndex).toUpper());
		return setter.toString();
	}

	private String toSetter(IJavaName name) {
		StringBuilder setter = new StringBuilder();
		List<IJavaName> nameList = name.split();
		int setIndex = nameList.size() - 1;
		for (int getIndex = 0; getIndex < setIndex; getIndex++) {
			setter.append("get");
			setter.append(nameList.get(getIndex).toUpper());
			setter.append("().");
		}
		setter.append("set");
		setter.append(nameList.get(setIndex).toUpper());
		return setter.toString();
	}
}
