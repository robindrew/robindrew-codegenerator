package com.robindrew.codegenerator.lang.java.type.block.method;

import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.ABSTRACT;
import static com.robindrew.codegenerator.lang.java.type.block.keyword.JavaKeyword.STATIC;

import java.util.List;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.annotation.IJavaAnnotation;
import com.robindrew.codegenerator.lang.java.type.block.annotation.IJavaAnnotationSet;
import com.robindrew.codegenerator.lang.java.type.block.annotation.JavaAnnotation;
import com.robindrew.codegenerator.lang.java.type.block.annotation.JavaAnnotationSet;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeBlock;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.JavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.comment.IMultiComment;
import com.robindrew.codegenerator.lang.java.type.block.comment.MultiComment;
import com.robindrew.codegenerator.lang.java.type.block.parameter.IJavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedTypeSet;
import com.robindrew.codegenerator.lang.java.type.block.visibility.IJavaVisibility;
import com.robindrew.codegenerator.lang.java.type.block.visibility.JavaVisibility;
import com.robindrew.codegenerator.lang.java.type.name.IJavaName;
import com.robindrew.codegenerator.lang.java.type.name.JavaName;
import com.robindrew.codegenerator.lang.java.type.set.IJavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.set.JavaTypeSet;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaWriter;

public class JavaMethod implements IJavaMethod {

	private final IJavaName name;
	private final IJavaType returnType;
	private IJavaVisibility visibility = JavaVisibility.PUBLIC;
	private boolean isStatic = false;
	private boolean isAbstract = false;
	private boolean isConstructor = false;
	private boolean isInterface = false;
	private IJavaCodeBlock contents = null;
	private final IJavaTypeSet references = new JavaTypeSet();
	private final IJavaAnnotationSet annotations = new JavaAnnotationSet();
	private final IMultiComment comment = new MultiComment();
	private final IJavaNamedTypeSet parameters;
	private final IJavaTypeSet throwsSet = new JavaTypeSet();

	public JavaMethod(IJavaName name, IJavaType returnType) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		if (returnType == null) {
			throw new NullPointerException("returnType");
		}
		this.name = name;
		this.returnType = returnType;
		this.parameters = new JavaNamedTypeSet(references);
		getReferences().add(returnType);
	}

	public JavaMethod(IJavaName name) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		this.name = name;
		this.returnType = JavaTypeClass.VOID;
		this.parameters = new JavaNamedTypeSet(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaMethod(IJavaName name, Class<?> returnType) {
		this(name, new JavaTypeClass(returnType));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JavaMethod(String name, Class<?> returnType) {
		this(new JavaName(name), new JavaTypeClass(returnType));
	}

	public JavaMethod(String name, IJavaType returnType) {
		this(new JavaName(name), returnType);
	}

	public JavaMethod(String name) {
		this(new JavaName(name));
	}

	@Override
	public JavaMethod setOverride() {
		getAnnotations().add(JavaAnnotation.OVERRIDE);
		return this;
	}

	@Override
	public IMultiComment getComment() {
		return comment;
	}

	@Override
	public IJavaName getName() {
		return name;
	}

	@Override
	public IJavaType getReturnType() {
		return returnType;
	}

	@Override
	public IJavaNamedTypeSet getParameters() {
		return parameters;
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
	public boolean isInterface() {
		return isInterface;
	}

	@Override
	public boolean isConstructor() {
		return isConstructor;
	}

	@Override
	public boolean isAbstract() {
		return isAbstract;
	}

	@Override
	public JavaMethod setAbstract(boolean value) {
		this.isAbstract = value;
		return this;
	}

	@Override
	public JavaMethod setInterface(boolean value) {
		this.isInterface = value;
		return this;
	}

	@Override
	public JavaMethod setConstructor(boolean value) {
		this.isConstructor = value;
		return this;
	}

	@Override
	public JavaMethod setVisibility(IJavaVisibility visibility) {
		if (visibility == null) {
			throw new NullPointerException("visibility");
		}
		this.visibility = visibility;
		return this;
	}

	@Override
	public JavaMethod setStatic(boolean value) {
		this.isStatic = value;
		return this;
	}

	@Override
	public IJavaCodeBlock getContents() {
		return contents;
	}

	@Override
	public boolean hasContents() {
		return contents != null;
	}

	@Override
	public void setContents(IJavaCodeBlock block) {
		this.contents = block;
	}

	@Override
	public IJavaTypeSet getReferences() {
		references.addAll(throwsSet);
		return references;
	}

	@Override
	public IJavaAnnotationSet getAnnotations() {
		return annotations;
	}

	@Override
	public IJavaTypeSet getThrowsSet() {
		return throwsSet;
	}

	@Override
	public int hashCode() {
		int hash = 1999 * getName().hashCode() * getReturnType().hashCode();
		for (IJavaNamedType type : getParameters()) {
			hash *= type.hashCode();
		}
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof IJavaMethod) {
			IJavaMethod method = (IJavaMethod) object;
			if (!getName().equals(method.getName())) {
				return false;
			}
			if (!getReturnType().equals(method.getReturnType())) {
				return false;
			}
			if (!getParameters().equals(method.getParameters())) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void writeTo(IJavaWriter writer) {
		if (!comment.isEmpty()) {
			comment.writeTo(writer);
		}

		// Annotations
		for (IJavaAnnotation annotation : getAnnotations()) {
			annotation.writeTo(writer);
		}

		writer.indent();

		// Modifiers
		if (!isInterface()) {
			writer.write(getVisibility());
			writer.write(STATIC, isStatic());
			writer.write(ABSTRACT, isAbstract());
		}

		if (isConstructor()) {
			// Constructor name
			writer.write(getName().toUpper());
		} else {
			// Return type
			writer.write(getReturnType()).space();
			// Method name
			writer.write(getName().toLower());
		}

		// Parameters
		writer.write('(');
		boolean comma = false;
		for (IJavaNamedType parameter : getParameters()) {
			if (comma) {
				writer.write(", ");
			}
			comma = true;
			parameter.writeTo(writer);
		}
		writer.write(')');

		// Exceptions?
		if (!getThrowsSet().isEmpty()) {
			writer.write(" throws ");
			comma = false;
			for (IJavaType type : getThrowsSet()) {
				if (comma) {
					writer.write(", ");
				}
				comma = true;
				writer.write(type.getSimpleName(true));
			}
		}

		// Abstract?
		if (isAbstract() || isInterface()) {
			writer.semi();
			writer.line();
			return;
		}

		// Contents
		writer.write(" {");
		writer.line();
		if (hasContents()) {
			writer.shiftIndent(1);
			getContents().writeTo(writer);
			writer.shiftIndent(-1);
		}
		writer.indent();
		writer.write("}");
		writer.line();
	}

	public JavaMethod setUnsupportedOperationContents() {
		IJavaCodeLines code = new JavaCodeLines();
		code.line("throw new UnsupportedOperationException(\"" + getName() + "\");");
		setContents(code);
		return this;
	}

	@Override
	public String toString() {
		return getReturnType() + " " + getName() + "()";
	}

	public boolean matches(IJavaMethod method) {
		// Ignores return type
		if (!matches(getParameters(), method.getParameters())) {
			return false;
		}
		if (!getName().toLower().get().equals(method.getName().toLower().get())) {
			return false;
		}
		return true;
	}

	private boolean matches(IJavaNamedTypeSet parameters1, IJavaNamedTypeSet parameters2) {
		if (parameters1.size() != parameters2.size()) {
			return false;
		}
		List<IJavaNamedType> list1 = parameters1.getAll();
		List<IJavaNamedType> list2 = parameters2.getAll();
		for (int i = 0; i < list1.size(); i++) {
			IJavaNamedType type1 = list1.get(i);
			IJavaNamedType type2 = list2.get(i);

			// Generic symbols match any type
			if (type1.getType().isGenericSymbol()) {
				continue;
			}
			if (type2.getType().isGenericSymbol()) {
				continue;
			}

			// Do the types match? ignore the names
			if (!type1.getType().equals(type2.getType())) {
				return false;
			}
		}
		return true;
	}
}
