package com.robindrew.codegenerator.lang.java.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;

/**
 * A type where you can specify a type and a list of its generics (e.g. Map&lt;String, Integer&gt;)
 */
public class JavaTypeWithGenerics extends AbstractJavaType {

	private final IJavaType delegate;
	private final List<IJavaType> genericsList = new ArrayList<IJavaType>();

	public JavaTypeWithGenerics(IJavaType underlying, List<IJavaType> genericsList) {
		super(underlying.getPackageName(), underlying.getSimpleName());
		this.delegate = underlying;

		if (genericsList.isEmpty()) {
			throw new IllegalArgumentException("genericsList is empty");
		}
		if (underlying.getGenericsCount() == UNKNOWN_GENERICS_COUNT) {
			throw new IllegalArgumentException("underlying is not generics aware: " + underlying);
		}
		if (underlying.getGenericsCount() != genericsList.size()) {
			throw new IllegalArgumentException("incorrect number of generics for underlying: " + underlying + ", expected=" + underlying.getGenericsCount() + ", actual=" + genericsList.size());
		}

		// Clone and convert to object types
		for (IJavaType type : genericsList) {
			this.genericsList.add(type.toObjectType());
		}
	}

	public JavaTypeWithGenerics(IJavaType underlying, IJavaType... genericsList) {
		this(underlying, Arrays.asList(genericsList));
	}

	@Override
	public IClassType getClassType() {
		return delegate.getClassType();
	}

	@Override
	public List<IJavaType> getGenericsList() {
		return genericsList;
	}

	@Override
	public IJavaType getUnderlying() {
		return delegate;
	}

	@Override
	public List<IJavaType> getUnderlyingTypes() {
		List<IJavaType> list = new ArrayList<IJavaType>();
		addType(this, list);
		return list;
	}

	private void addType(IJavaType type, List<IJavaType> list) {
		list.add(type.getUnderlying());
		for (IJavaType generic : type.getGenericsList()) {
			addType(generic, list);
		}
	}

	@Override
	public Class<?> getType() {
		return delegate.getType();
	}

	@Override
	public boolean isInstanceOf(Class<?> type) {
		return delegate.isInstanceOf(type);
	}

	@Override
	public boolean isType(Class<?>... types) {
		return delegate.isType(types);
	}

	@Override
	public IJavaType getEnclosingClass() {
		return delegate.getEnclosingClass();
	}
}
