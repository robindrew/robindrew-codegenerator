package com.robindrew.codegenerator.lang.java.generator.object.datastore.method.cachedpersister;

import com.robindrew.codegenerator.lang.java.type.block.codeblock.IJavaCodeLines;
import com.robindrew.codegenerator.lang.java.type.block.field.IJavaField;
import com.robindrew.codegenerator.lang.java.type.block.method.IJavaMethod;
import com.robindrew.codegenerator.lang.java.type.block.method.delegate.DelegateMethod;

public class CachedPersisterMethod extends DelegateMethod {

	private final IJavaField cache;
	private final IJavaField persister;

	public CachedPersisterMethod(IJavaField cache, IJavaField persister, IJavaMethod method) {
		super(method);
		this.cache = cache;
		this.persister = persister;
		setDelegateContents();
		setOverride();
	}

	public IJavaCodeLines setDelegateContents(IJavaCodeLines code) {
		String call = getDelegateCall(false);
		code.line(cache.getName() + "." + call + ";");
		code.line(persister.getName() + "." + call + ";");
		return code;
	}
}
