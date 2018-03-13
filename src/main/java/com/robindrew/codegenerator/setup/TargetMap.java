package com.robindrew.codegenerator.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.robindrew.codegenerator.setup.target.ITarget;
import com.robindrew.codegenerator.setup.target.ITargetMap;

public class TargetMap implements ITargetMap {

	private final Map<String, ITarget> map = new HashMap<String, ITarget>();

	public TargetMap(List<ITarget> list) {
		for (ITarget target : list) {
			map.put(target.getName(), target);
		}
	}

	public ITarget get(String name) {
		ITarget target = map.get(name);
		if (target == null) {
			throw new IllegalArgumentException("target not defined: '" + name + "'");
		}
		return target;
	}

}
