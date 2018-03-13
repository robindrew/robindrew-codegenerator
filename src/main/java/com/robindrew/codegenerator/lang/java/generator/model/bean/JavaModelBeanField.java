package com.robindrew.codegenerator.lang.java.generator.model.bean;

import java.util.List;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModelTyped;
import com.robindrew.codegenerator.lang.java.generator.model.validator.JavaModelValidator;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.codegenerator.model.object.bean.ModelBeanField;

public class JavaModelBeanField extends JavaModelTyped<ModelBeanField> {

	private volatile JavaModelValidator validator = null;
	private volatile List<JavaModelBeanAnnotation> annotationList = null;
	private volatile boolean unique = false;

	public JavaModelBeanField(ModelBeanField field) {
		super(field);
	}

	public String getName() {
		return get().getName();
	}

	public boolean isNumeric() {
		return getType().isPrimitive() && getType().toObjectType().isInstanceOf(Number.class);
	}

	public Class<?> getNumericType() {
		return getType().toObjectType().getType();
	}

	public JavaModelValidator getValidator() {
		return validator;
	}

	public List<JavaModelBeanAnnotation> getAnnotationList() {
		return annotationList;
	}

	public void setValidator(JavaModelValidator validator) {
		if (validator == null) {
			throw new NullPointerException("validator");
		}
		this.validator = validator;
	}

	public void setAnnotationList(List<JavaModelBeanAnnotation> annotationList) {
		if (annotationList == null) {
			throw new NullPointerException("annotationList");
		}
		this.annotationList = annotationList;
	}

	public boolean isUnique() {
		return unique || get().isUnique();
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isAutoIncrement() {
		return get().isAutoIncrement();
	}

	public boolean isIdentity() {
		return get().isIdentity();
	}

	public IJavaNamedType toNamedType() {
		return new JavaNamedType(getName(), getType());
	}

}
