package com.robindrew.codegenerator.lang.java.type.resolver;

import static com.robindrew.codegenerator.lang.java.type.AbstractJavaType.EXTENDS;
import static com.robindrew.codegenerator.lang.java.type.AbstractJavaType.SUPER;
import static com.robindrew.codegenerator.lang.java.type.AbstractJavaType.UNKNOWN_GENERICS_COUNT;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.JavaTypeContainer;
import com.robindrew.codegenerator.lang.java.type.JavaTypeGenericSymbol;
import com.robindrew.codegenerator.lang.java.type.JavaTypeObject;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.classtype.IClassType;
import com.robindrew.codegenerator.lang.java.type.generics.Generics;
import com.robindrew.codegenerator.lang.java.type.generics.GenericsParser;

public class JavaTypeResolver implements IJavaTypeResolver {

	private static final Logger log = LoggerFactory.getLogger(JavaTypeResolver.class);

	private final ConcurrentMap<Class<?>, IJavaType> classMap = new ConcurrentHashMap<Class<?>, IJavaType>();
	private final ConcurrentMap<String, IJavaType> aliasMap = new ConcurrentHashMap<String, IJavaType>();

	@Override
	public IJavaType registerAlias(IJavaType type, String alias) {
		IJavaType alreadyRegistered = aliasMap.putIfAbsent(alias, type);

		// An alias can only ever by registered once
		if (alreadyRegistered != null && !alreadyRegistered.equals(type)) {
			throw new IllegalArgumentException("Alias already registered: '" + alias + "'");
		}

		if (alreadyRegistered == null && !type.getSimpleName(false).equals(alias)) {
			log.info("[Registered Alias] {} -> {}", alias, type);
		}

		// Register the type name as well if not the same
		String fullAlias = type.getName();
		if (!fullAlias.equals(alias)) {
			return registerAlias(type, fullAlias);
		}

		return type;
	}

	@Override
	public IJavaType registerAlias(IJavaType type) {
		return registerAlias(type, type.getSimpleName());
	}

	@Override
	public <T> IJavaType registerClass(Class<T> clazz, int generics) {
		IJavaType type = new JavaTypeClass<T>(clazz, generics);
		IJavaType alreadyRegistered = classMap.putIfAbsent(clazz, type);

		// Duplicate registration is fine, as long as they match!
		if (alreadyRegistered != null) {
			if (!alreadyRegistered.equals(type)) {
				throw new IllegalArgumentException("Class already registered but does not match: " + type + " != " + alreadyRegistered);
			}
			return alreadyRegistered;
		} else {
			if (generics > 0) {
				log.info("[Registered Type] {}<{}>", type, getGenericsString(generics));
			} else {
				log.info("[Registered Type] {}", type);
			}
		}
		return type;
	}

	private String getGenericsString(int generics) {
		StringBuilder text = new StringBuilder();
		text.append("?");
		for (int i = 1; i < generics; i++) {
			text.append(",?");
		}
		return text.toString();
	}

	@Override
	public <T> IJavaType registerClass(Class<T> clazz) {
		return registerClass(clazz, UNKNOWN_GENERICS_COUNT);
	}

	@Override
	public <T> IJavaType registerAlias(Class<T> clazz, String alias, int generics) {
		if (alias.isEmpty()) {
			throw new IllegalArgumentException("alias is empty");
		}
		IJavaType type = registerClass(clazz, generics);
		return registerAlias(type, alias);
	}

	@Override
	public <T> IJavaType registerAlias(Class<T> clazz, String alias) {
		return registerAlias(clazz, alias, UNKNOWN_GENERICS_COUNT);
	}

	@Override
	public <T> IJavaType registerAlias(Class<T> clazz) {
		return registerAlias(clazz, clazz.getSimpleName(), UNKNOWN_GENERICS_COUNT);
	}

	@Override
	public <T> IJavaType registerAlias(Class<T> clazz, int generics) {
		return registerAlias(clazz, clazz.getSimpleName(), generics);
	}

	public IJavaTypeResolver registerCommonTypes() {

		// Void
		registerAlias(JavaTypeClass.VOID);

		// Primitives
		registerAlias(byte.class, 0);
		registerAlias(short.class, 0);
		registerAlias(int.class, 0);
		registerAlias(long.class, 0);
		registerAlias(float.class, 0);
		registerAlias(double.class, 0);
		registerAlias(boolean.class, 0);
		registerAlias(char.class, 0);

		// Arrays
		registerAlias(byte[].class, 0);
		registerAlias(short[].class, 0);
		registerAlias(int[].class, 0);
		registerAlias(long[].class, 0);
		registerAlias(float[].class, 0);
		registerAlias(double[].class, 0);
		registerAlias(char[].class, 0);
		registerAlias(boolean[].class, 0);

		// Objects
		registerAlias(Byte.class, 0);
		registerAlias(Short.class, 0);
		registerAlias(Integer.class, 0);
		registerAlias(Long.class, 0);
		registerAlias(Float.class, 0);
		registerAlias(Double.class, 0);
		registerAlias(Boolean.class, 0);
		registerAlias(Character.class, 0);
		registerAlias(Object.class, 0);
		registerAlias(Class.class, 1);
		registerAlias(String.class, 0);
		registerAlias(Date.class, 0);
		registerAlias(Number.class, 0);
		registerAlias(Comparable.class, 1);
		registerAlias(Comparator.class, 1);
		registerAlias(Serializable.class, 0);
		registerAlias(File.class, 0);
		registerAlias(Runnable.class, 0);

		// Collections
		registerAlias(Collection.class, 1);
		registerAlias(Queue.class, 1);
		registerAlias(Deque.class, 1);
		registerAlias(List.class, 1);
		registerAlias(ArrayList.class, 1);
		registerAlias(LinkedList.class, 1);
		registerAlias(Map.class, 2);
		registerAlias(HashMap.class, 2);
		registerAlias(LinkedHashMap.class, 2);
		registerAlias(TreeMap.class, 2);
		registerAlias(Set.class, 1);
		registerAlias(HashSet.class, 1);
		registerAlias(LinkedHashSet.class, 1);
		registerAlias(TreeSet.class, 1);

		// Enums
		registerAlias(TimeUnit.class);

		// Special
		registerAlias(IExecutableBean.class, 1);

		// Generics
		registerAlias(new JavaTypeGenericSymbol("?"), "?");

		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public IJavaType parseClassType(String text) {
		try {
			Class clazz = Class.forName(text.trim());
			return new JavaTypeClass(clazz);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public IJavaType resolveJavaType(String text) {
		text = text.trim();

		// Replace alternative braces with correct ones
		text = replaceBrackets(text);

		// Is this a registered alias?
		IJavaType type = aliasMap.get(text);
		if (type != null) {
			return type;
		}

		// Is this a class?
		type = parseClassType(text);
		if (type != null) {
			return type;
		}

		// Is this a generic symbol?
		if (text.length() == 1 && Character.isUpperCase(text.charAt(0))) {
			return new JavaTypeGenericSymbol(text);
		}

		// Is this an array?
		if (text.endsWith("[]")) {
			return resolveArrayType(text);
		}

		// Are there generics to parse?
		if (!text.contains("<")) {
			throw new IllegalArgumentException("Unable to resolve type: '" + text + "'");
		}
		try {
			Generics generics = new GenericsParser().parseGenerics(text);
			return resolveJavaType(generics);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to resolve type: '" + text + "'", e);
		}
	}

	private IJavaType resolveArrayType(String text) {
		int index = text.indexOf("[]");
		int dimensions = (text.length() - index) / 2;
		String component = text.substring(0, index);
		IJavaType type = resolveJavaType(component);
		return new JavaTypeObject(type.getPackageName(), text, type.getClassType(), 0, type, dimensions);
	}

	private IJavaType resolveJavaType(Generics generics) {

		// Type
		String name = generics.getName();

		// ? extends or super
		boolean isExtends = false;
		boolean isSuper = false;
		if (name.startsWith(EXTENDS)) {
			name = name.substring(EXTENDS.length());
			isExtends = true;
		}
		if (name.startsWith(SUPER)) {
			name = name.substring(SUPER.length());
			isSuper = true;
		}

		IJavaType underlying = resolveJavaType(name);
		if (generics.isEmpty()) {
			return getType(underlying, isExtends, isSuper);
		}

		// Generic Parameters
		List<IJavaType> genericsList = new ArrayList<IJavaType>();
		for (Generics child : generics.getGenericsList()) {
			IJavaType childType = resolveJavaType(child);
			if (childType.isPrimitive()) {
				childType = childType.toObjectType();
				// throw new IllegalStateException("Generic types can not be primitive: " + underlying + "<" + childType
				// + "...>");
			}
			genericsList.add(childType);
		}
		IJavaType type = new JavaTypeWithGenerics(underlying, genericsList);
		return getType(type, isExtends, isSuper);
	}

	private IJavaType getType(IJavaType type, boolean isExtends, boolean isSuper) {
		if (isExtends || isSuper) {
			return new JavaTypeContainer(type, isExtends, isSuper);
		}
		return type;
	}

	@Override
	public Set<IJavaType> getRegisteredTypes() {
		Set<IJavaType> set = new HashSet<IJavaType>();
		set.addAll(classMap.values());
		set.addAll(aliasMap.values());
		return set;
	}

	@Override
	public <T> IJavaType getType(Class<T> clazz) {
		IJavaType type = classMap.get(clazz);
		if (type == null) {
			throw new IllegalArgumentException("Type not registered: " + clazz);
		}
		return type;
	}

	@Override
	public IJavaType registerAndAliasJavaType(String packageName, String simpleName, IClassType classType) {
		String text = simpleName;

		text = text.trim();

		// Replace alternative braces with correct ones
		text = replaceBrackets(text);

		// Is this a registered alias?
		IJavaType type = aliasMap.get(text);
		if (type != null) {
			return type;
		}

		// Is this a class?
		type = parseClassType(text);
		if (type != null) {
			return type;
		}

		// Is this a generic symbol?
		if (text.length() == 1 && Character.isUpperCase(text.charAt(0))) {
			return new JavaTypeGenericSymbol(text);
		}

		// Register the basic type
		Generics generics = new GenericsParser().parseGenerics(text);
		type = new JavaTypeObject(packageName, generics.getName(), classType, generics.size());
		registerAlias(type);

		if (generics.isEmpty()) {
			return type;
		}
		try {
			return resolveJavaType(generics);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to resolve type: '" + text + "'", e);
		}
	}

	private String replaceBrackets(String text) {
		text = text.replace('{', '<');
		text = text.replace('}', '>');
		return text;
	}

}
