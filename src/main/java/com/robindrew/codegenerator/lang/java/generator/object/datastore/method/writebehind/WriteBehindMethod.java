package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.writebehind;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.robindrew.codegenerator.lang.java.type.IJavaType;
import com.robindrew.codegenerator.lang.java.type.JavaTypeClass;
import com.robindrew.codegenerator.lang.java.type.block.IJavaNamedType;
import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;
import com.robindrew.codegenerator.lang.java.type.block.parameter.JavaNamedType;
import com.robindrew.common.util.Java;

public class WriteBehindMethod extends DelegateMethod {

	private final boolean forceSync;

	public WriteBehindMethod(IJavaMethod method) {
		this(method, false);
	}

	public WriteBehindMethod(IJavaMethod method, boolean forceSync) {
		super(method);
		if (!getReturnType().isVoid()) {
			getReferences().add(Future.class);
			getReferences().add(Callable.class);
			getReferences().add(Java.class);
		}
		this.forceSync = forceSync;
		setDelegateContents();
		setOverride();
	}

	public WriteBehindMethod setParametersFinal() {
		List<IJavaNamedType> list = getParameters().getAll();
		getParameters().clear();
		for (IJavaNamedType type : list) {
			if (!type.isFinal()) {
				type = new JavaNamedType(type.getName(), type.getType(), true);
			}
			getParameters().add(type);
		}
		return this;
	}

	@Override
	public IJavaCodeLines setDelegateContents(IJavaCodeLines code) {
		IJavaType type = getReturnType().toObjectType();
		if (type.isVoid() && !forceSync) {
			setAsyncContents(code);
		} else {
			setSyncContents(type, code);
		}
		return code;
	}

	public void setAsyncContents(IJavaCodeLines code) {
		code.line("// Aysyncronous (Write Behind)");
		code.line("executor.submit(new Runnable() {");
		code.line(1, "public void run() {");
		code.line(2, getDelegateCall() + ";");
		code.line(1, "}");
		code.line("});");
	}

	private void setSyncContents(IJavaType type, IJavaCodeLines code) {
		boolean isVoid = type.isVoid();
		if (isVoid) {
			type = new JavaTypeClass<Object>(Object.class);
		}

		type.toString();

		code.line("// Synchronous");
		code.line("Future<" + type + "> future = executor.submit(new Callable<" + type + ">() {");
		code.line(1, "public " + type + " call() {");
		if (isVoid) {
			code.line(2, getDelegateCall() + ";");
			code.line(2, "return null;");
		} else {
			code.line(2, "return " + getDelegateCall() + ";");
		}
		code.line(1, "}");
		code.line("});");
		code.emptyLine();
		code.line("try {");
		if (isVoid) {
			code.line(1, "future.get();");
		} else {
			code.line(1, "return future.get();");
		}
		code.line("} catch(Exception e) {");
		code.line(1, "throw Java.propagate(e);");
		code.line("}");
	}

}
