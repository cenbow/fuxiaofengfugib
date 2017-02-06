package com.cqliving.codegenerator.module;

import com.cqliving.codegenerator.GenerateConfiguration;
import com.cqliving.codegenerator.GenerateContext;

/**
 * Domain generator
 * 
 * @author zhangpu
 * 
 */
public class ServiceInterfaceModuleGenerator extends FreeMarkerModuleGenerator {

	@Override
	protected String getOutputPath(GenerateContext generateContext, String template) {
		GenerateConfiguration cfg = getGenerateConfiguration();
		String packagePath = getPackagePath(generateContext.getNames().getServicePackage());
		return cfg.getWorkspace() + "/" + cfg.getCodePath() + "/" + packagePath;
	}

	@Override
	protected String getOutputFile(GenerateContext generateContext, String template) {
		return generateContext.getNames().getServiceClassName() + ".java";
	}

}
