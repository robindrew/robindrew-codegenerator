package com.robindrew.codegenerator.lang.java.generator.object;

import com.robindrew.codegenerator.lang.java.generator.model.JavaModel;
import com.robindrew.codegenerator.lang.java.generator.object.context.IJavaContext;
import com.robindrew.codegenerator.lang.java.type.writable.IJavaTypedWritable;
import com.robindrew.codegenerator.model.object.IModelObject;
import com.robindrew.codegenerator.setup.ISetup;
import com.robindrew.codegenerator.setup.target.ITarget;

public abstract class JavaObjectGenerator extends JavaGenerator {

	private final IModelObject object;

	protected JavaObjectGenerator(ISetup setup, IJavaContext context, JavaModel model, IModelObject object) {
		super(setup, context, model);
		if (object == null) {
			throw new NullPointerException("object");
		}
		this.object = object;
	}

	protected ITarget getTarget() {
		String target = getModel().get().getTarget();

		// Individual objects can override the target
		if (object != null && object.getTarget() != null) {
			target = object.getTarget();
		}
		return getSetup().getTargetMap().get(target);
	}

	public void write(IJavaTypedWritable writable) {

		// Where are we writing to?
		ITarget target = getTarget();
		target.getWriter().write(writable);
		getContext().getGeneratorStats().generatedClass();
	}

}
