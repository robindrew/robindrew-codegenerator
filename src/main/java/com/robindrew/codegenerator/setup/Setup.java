package com.robindrew.codegenerator.setup;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.robindrew.codegenerator.setup.source.ISource;
import com.robindrew.codegenerator.setup.source.Source;
import com.robindrew.codegenerator.setup.target.ITarget;
import com.robindrew.codegenerator.setup.target.ITargetMap;
import com.robindrew.codegenerator.setup.target.Target;

@Root
public class Setup implements ISetup {

	@ElementList(entry = "Source", inline = true, type = Source.class)
	private List<ISource> sourceList = new ArrayList<ISource>();
	@ElementList(entry = "Target", inline = true, type = Target.class)
	private List<ITarget> targetList = new ArrayList<ITarget>();

	private volatile ITargetMap map = null;

	@Override
	public List<ITarget> getTargetList() {
		return targetList;
	}

	@Override
	public List<ISource> getSourceList() {
		return sourceList;
	}

	@Override
	public ITargetMap getTargetMap() {
		if (map == null) {
			map = new TargetMap(targetList);
		}
		return map;
	}

}
