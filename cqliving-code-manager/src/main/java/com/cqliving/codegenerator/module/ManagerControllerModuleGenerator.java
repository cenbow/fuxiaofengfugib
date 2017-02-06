package com.cqliving.codegenerator.module;

import com.cqliving.codegenerator.GenerateConfiguration;
import com.cqliving.codegenerator.GenerateContext;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class ManagerControllerModuleGenerator extends FreeMarkerModuleGenerator {

	@Override
	protected String getOutputPath(GenerateContext generateContext, String temp) {
		GenerateConfiguration cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNames().getControllerPackage());
		return cfg.getWebWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String temp) {
		return generateContext.getNames().getControllerClassName() + ".java";
	}

}
