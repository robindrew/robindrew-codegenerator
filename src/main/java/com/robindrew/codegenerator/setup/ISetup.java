package com.robindrew.codegenerator.setup;

import java.util.List;

import com.robindrew.codegenerator.setup.source.ISource;
import com.robindrew.codegenerator.setup.target.ITarget;
import com.robindrew.codegenerator.setup.target.ITargetMap;

public interface ISetup {

	List<ISource> getSourceList();

	List<ITarget> getTargetList();

	ITargetMap getTargetMap();

}
