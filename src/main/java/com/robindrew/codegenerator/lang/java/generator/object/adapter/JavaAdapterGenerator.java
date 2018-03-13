package com.robindrew.codegenerator.lang.java.generator.object.adapter;

import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;

import java.util.LinkedHashMap;
import java.util.Map;

import com.robindrew.codegenerator.api.adaptor.IAdapter;
import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.JavaObjectGenerator;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeWithGenerics;
import com.robindrew.codegenerator.lang.java.type.block.method.adapt.AdaptMethod;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.object.JavaObject;
import com.robindrew.codegenerator.model.object.adapter.ModelAdapter;
import com.robindrew.codegenerator.model.object.adapter.ModelAdapterField;
import com.robindrew.codegenerator.setup.ISetup;

public class JavaAdapterGenerator extends JavaObjectGenerator {

	private final ModelAdapter adapter;

	private IJavaType objectType = null;
	private IJavaType fromType = null;
	private IJavaType toType = null;

	public JavaAdapterGenerator(ISetup setup, IJavaContext context, JavaModel model, ModelAdapter adapter) {
		super(setup, context, model, adapter);
		if (adapter == null) {
			throw new NullPointerException("adapter");
		}
		if (adapter.getFieldList().isEmpty()) {
			throw new IllegalArgumentException("adapter has no fields: " + adapter.getName());
		}
		this.adapter = adapter;
	}

	public ModelAdapter getAdapter() {
		return adapter;
	}

	@Override
	public void registerPrimaryTypes() {
		// Register this adapter as a type
		objectType = registerJavaType(getAdapter().getName(), CLASS);
	}

	@Override
	public void verifyReferencedTypes() {
		fromType = resolve(getAdapter().getFrom());
		toType = resolve(getAdapter().getTo());
	}

	@Override
	public void generate() {

		// For each adapter we generate a class
		JavaObject object = new JavaObject(objectType);
		object.addExtends(getAdapterInterface());

		// Methods
		addBlocks(object);

		// Done!
		write(object);
	}

	private IJavaType getAdapterInterface() {
		IJavaType adapter = resolve(IAdapter.class, 2);
		return new JavaTypeWithGenerics(adapter, fromType, toType);
	}

	private IJavaType getToInstanceType() {
		if (!toType.isInterface()) {
			return toType;
		}
		return getContext().getExtendsMap().getInstance(toType);
	}

	private void addBlocks(JavaObject object) {
		Map<IJavaName, IJavaName> fieldMap = getFieldMap();
		object.addBlock(new AdaptMethod(fromType, toType, getToInstanceType(), fieldMap).setOverride());
	}

	private Map<IJavaName, IJavaName> getFieldMap() {
		Map<IJavaName, IJavaName> map = new LinkedHashMap<IJavaName, IJavaName>();
		for (ModelAdapterField field : adapter.getFieldList()) {
			IJavaName from = new JavaName(field.getFrom());
			IJavaName to = new JavaName(field.getTo());
			map.put(from, to);
		}
		return map;
	}

}
