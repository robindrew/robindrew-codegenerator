package com.robindrew.codegenerator.lang.java.type.block.parameter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaNamedType implements IJavaNamedType {

	private final IJavaName name;
	private final IJavaType type;
	private final boolean isFinal;

	public JavaNamedType(IJavaName name, IJavaType type, boolean isFinal) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.name = name;
		this.type = type;
		this.isFinal = isFinal;
	}

	public JavaNamedType(IJavaName name, IJavaType type) {
		this(name, type, false);
	}

	public JavaNamedType(String name, IJavaType type) {
		this(new JavaName(name), type);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaNamedType(String name, Class<?> type) {
		this(new JavaName(name), new JavaTypeClass(type));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaNamedType(IJavaName name, Class<?> type) {
		this(name, new JavaTypeClass(type));
	}

	public JavaNamedType(IJavaNamedType namedType) {
		this(namedType.getName(), namedType.getType());
	}

	public JavaNamedType(String name, IJavaType type, boolean isFinal) {
		this(new JavaName(name), type, isFinal);
	}

	@Override
	public IJavaType getType() {
		return type;
	}

	@Override
	public IJavaName getName() {
		return name;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(name);
		builder.append(type);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof IJavaNamedType) {
			IJavaNamedType that = (IJavaNamedType) object;

			EqualsBuilder builder = new EqualsBuilder();
			builder.append(name, that.getName());
			builder.append(type, that.getType());
			return builder.isEquals();
		}
		return false;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (isFinal) {
			writer.write("final ");
		}
		writer.write(type.getDeclaration(false));
		writer.space();
		writer.write(name);
	}

	@Override
	public String toString() {
		return name + "[" + type + "]";
	}

	@Override
	public boolean isFinal() {
		return isFinal;
	}

}
