package com.robindrew.codegenerator.lang.java.type.object;

import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.ABSTRACT;
import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.STATIC;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.CLASS;
import static com.robindrew.codegenerator.lang.java.type.classtype.ClassType.INTERFACE;
import static java.util.Collections.unmodifiableSet;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.block.IJavaClassBlock;
import com.robindrew.codegenerator.lang.java.type.block.eenum.IJavaEnumField;
import com.robindrew.codegenerator.lang.java.type.block.field.IJavaField;
import com.robindrew.codegenerator.lang.java.type.block.visibility.IJavaVisibility;
import com.robindrew.codegenerator.lang.java.type.block.visibility.JavaVisibility;
import com.robindrew.codegenerator.lang.java.type.classtype.ClassType;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaObject implements IJavaObject {

	private final IJavaType type;
	private boolean isStatic = false;
	private boolean isAbstract = false;
	private IJavaVisibility visibility = JavaVisibility.PUBLIC;
	private final Set<IJavaEnumField> enumFields = new LinkedHashSet<IJavaEnumField>();
	private final Set<IJavaClassBlock> blocks = new LinkedHashSet<IJavaClassBlock>();
	private final IJavaTypeSet extendsSet = new JavaTypeSet();
	private final IJavaTypeSet importSet = new JavaTypeSet(true);

	public JavaObject(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
	}

	public JavaObject(IJavaType type, IJavaObject clone) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		this.type = type;
		this.isStatic = clone.isStatic();
		this.isAbstract = clone.isAbstract();
		this.visibility = clone.getVisibility();
		this.enumFields.addAll(clone.getEnumFields());
		this.blocks.addAll(clone.getBlocks());
	}

	@Override
	public Set<IJavaEnumField> getEnumFields() {
		return unmodifiableSet(enumFields);
	}

	@Override
	public IJavaVisibility getVisibility() {
		return visibility;
	}

	@Override
	public boolean isStatic() {
		return isStatic;
	}

	@Override
	public boolean isAbstract() {
		return isAbstract;
	}

	@Override
	public void setAbstract(boolean value) {
		this.isAbstract = value;
	}

	@Override
	public void setVisibility(IJavaVisibility visibility) {
		if (visibility == null) {
			throw new NullPointerException("visibility");
		}
		this.visibility = visibility;
	}

	@Override
	public void setStatic(boolean value) {
		this.isStatic = value;
	}

	@Override
	public IJavaType getType() {
		return type;
	}

	@Override
	public Set<IJavaClassBlock> getBlocks() {
		return unmodifiableSet(blocks);
	}

	@Override
	public void addBlock(IJavaClassBlock block) {
		if (block == null) {
			throw new NullPointerException("block");
		}

		// Enum Field?
		if (block instanceof IJavaEnumField) {
			if (!enumFields.add((IJavaEnumField) block)) {
				throw new IllegalArgumentException("Duplicate enum field: " + block);
			}
			return;
		}

		// Standard block
		if (!blocks.add(block)) {
			throw new IllegalArgumentException("Duplicate block: " + block);
		}

		// Imports?
		for (IJavaType type : block.getReferences()) {
			addImport(type);
		}
	}

	@Override
	public void addBlocks(Iterable<? extends IJavaClassBlock> blocks) {
		for (IJavaClassBlock block : blocks) {
			addBlock(block);
		}
	}

	@Override
	public IJavaTypeSet getImports() {
		return importSet;
	}

	@Override
	public IJavaTypeSet getExtends() {
		return extendsSet;
	}

	@Override
	public void addImport(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}

		// Allow duplicates
		importSet.add(type);

		// Don't forget generics!
		for (IJavaType generic : type.getGenericsList()) {
			addImport(generic);
		}
	}

	@Override
	public void addExtends(IJavaType type) {
		if (type == null) {
			throw new NullPointerException("type");
		}
		if (!extendsSet.add(type)) {
			throw new IllegalArgumentException("Duplicate extends type: " + type);
		}
		addImport(type);
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		writer.indent();

		// Package
		if (!getType().isDefaultPackage()) {
			writer.write("package ");
			writer.write(getType().getPackageName()).write(';').line();
			writer.line();
		}

		// Imports
		writeImports(writer);

		// Modifiers
		writer.write(getVisibility());
		writer.write(STATIC, isStatic());
		writer.write(ABSTRACT, isAbstract());

		// Type
		getType().getClassType().writeTo(writer);

		// Class name
		writer.write(getType().getSimpleName(true));

		// Extends & Implements
		writeExtends(writer);

		writer.write(" {");
		writer.line();

		// Enum Fields
		writeEnumFields(writer);

		// Blocks
		writeBlocks(writer);

		writer.indent();
		writer.write("}");
		writer.line();
	}

	private void writeImports(IJavaWriter writer) {
		IJavaTypeSet set = new JavaTypeSet();
		for (IJavaClassBlock block : getBlocks()) {
			set.addAll(block.getReferences());
		}
		set.addAll(getExtends());
		set.addAll(getImports());

		boolean imported = false;
		for (IJavaType type : set) {
			if (type.getClassType().equals(ClassType.GENERIC_SYMBOL)) {
				continue;
			}
			if (type.isArray()) {
				type = type.getComponentType();
			}
			if (!type.requiresImport()) {
				continue;
			}
			if (type.isSamePackage(getType())) {
				continue;
			}
			writer.write("import ");
			IJavaType enclosing = type.getEnclosingClass();
			if (enclosing != null) {
				writer.write(enclosing.getName());
				writer.write('.');
				writer.write(type.getSimpleName(false));
			} else {
				writer.write(type.getName());
			}
			writer.semi();
			writer.line();
			imported = true;
		}
		if (imported) {
			writer.line();
		}
	}

	private void writeExtends(IJavaWriter writer) {
		if (getExtends().isEmpty()) {
			return;
		}

		// Extends
		if (getType().isInterface()) {
			writeExtends(writer, "extends", getExtends());
			return;
		}
		writeExtends(writer, "extends", getExtends().subSet(CLASS));

		// Implements
		writeExtends(writer, "implements", getExtends().subSet(INTERFACE));
	}

	private void writeExtends(IJavaWriter writer, String keyword, IJavaTypeSet types) {
		if (types.isEmpty()) {
			return;
		}
		writer.space();
		writer.write(keyword);
		boolean comma = false;
		for (IJavaType type : types) {
			if (comma) {
				writer.comma();
			}
			comma = true;
			writer.space();
			writer.write(type.getSimpleName(true));
		}
	}

	private void writeEnumFields(IJavaWriter writer) {
		if (getEnumFields().isEmpty()) {
			return;
		}
		writer.line();

		writer.shiftIndent(1);
		List<IJavaEnumField> list = new ArrayList<IJavaEnumField>(getEnumFields());
		for (int i = 0; i < list.size(); i++) {
			IJavaEnumField field = list.get(i);
			field.writeTo(writer);

			// Semicolon or comma?
			if (i == (list.size() - 1)) {
				writer.semi();
			} else {
				writer.comma();
			}
			writer.line();
		}
		writer.shiftIndent(-1);
	}

	private void writeBlocks(IJavaWriter writer) {
		IJavaClassBlock previous = null;
		for (IJavaClassBlock block : getBlocks()) {
			if (writeLine(previous, block)) {
				writer.line();
			}
			writer.shiftIndent(1);
			writer.write(block);
			writer.shiftIndent(-1);
			previous = block;
		}
	}

	private boolean writeLine(IJavaClassBlock previous, IJavaClassBlock next) {
		// No newlines between fields, unless they are static ...
		if (previous instanceof IJavaField && next instanceof IJavaField) {
			IJavaField previousField = (IJavaField) previous;
			IJavaField nextField = (IJavaField) next;
			return previousField.isStatic() != nextField.isStatic();
		}
		return true;
	}

}
