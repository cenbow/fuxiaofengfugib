package com.cqliving.codegenerator.parser;

import org.apache.commons.lang3.StringUtils;

import com.cqliving.codegenerator.GenerateConfiguration;

public class DefaultNamesResolver implements NamesResolver {

	/** 表或列名单词分割符，每个分割符后的单词首字母大写（驼峰规则） */
	private static final String WORD_SEPARATOR = "_";

	private GenerateConfiguration generateConfiguration;

	private String tableToEntityIgnorPrefix;

	private String daoPostfit = "Dao";
	private String daoImplPostfit = "DaoImpl";
	private String daoTestPostfit = "DaoTest";
	private String managerPostfit = "Manager";
	private String managerImplPostfit = "ManagerImpl";
	private String managerTestPostfit = "ManagerTest";
	private String servicePostfit = "Service";
	private String serviceImplPostfit = "ServiceImpl";
	private String serviceTestPostfit = "ServiceTest";
	private String controllerPostfit = "Controller";

	@Override
	public NamesHold resolve(String tableName) {
		NamesHold namesHold = new NamesHold();
		String baseName = convertBaseName(tableName);

		String rootPackage = getGenerateConfiguration().getRootPackage();
		namesHold.setDomainClassName(StringUtils.capitalize(baseName));
		namesHold.setDomainPackage(rootPackage + ".domain");

		namesHold.setDaoPackage(rootPackage + ".dao");
		namesHold.setDaoClassName(namesHold.getDomainClassName() + daoPostfit);

		namesHold.setDaoImplPackage(rootPackage + ".dao.impl");
		namesHold.setDaoImplClassName(namesHold.getDomainClassName() + daoImplPostfit);

		namesHold.setDaoTestPackage(namesHold.getDaoPackage());
		namesHold.setDaoTestClassName(namesHold.getDomainClassName() + daoTestPostfit);

		namesHold.setManagerPackage(rootPackage + ".manager");
		namesHold.setManagerClassName(namesHold.getDomainClassName() + managerPostfit);
		namesHold.setManagerImplPackage(rootPackage + ".manager.impl");
		namesHold.setManagerImplClassName(namesHold.getDomainClassName() + managerImplPostfit);

		namesHold.setManagerTestPackage(namesHold.getManagerPackage());
		namesHold.setManagerTestClassName(namesHold.getDomainClassName() + managerTestPostfit);

		namesHold.setServicePackage(rootPackage + ".service");
		namesHold.setServiceClassName(namesHold.getDomainClassName() + servicePostfit);
		namesHold.setServiceImplPackage(rootPackage + ".service.impl");
		namesHold.setServiceImplClassName(namesHold.getDomainClassName() + serviceImplPostfit);
		namesHold.setServiceTestPackage(namesHold.getServicePackage());
		namesHold.setServiceTestClassName(namesHold.getDomainClassName() + serviceTestPostfit);

		
		namesHold.setControllerPackage(generateConfiguration.getWebPackage());
		namesHold.setControllerClassName(namesHold.getDomainClassName() + controllerPostfit);

        namesHold.setPagePath(getGenerateConfiguration().getWebWorkspace()+"/"
                +getGenerateConfiguration().getWebappPath()
                +getGenerateConfiguration().getPagePath());
		return namesHold;
	}

	/**
	 * 表名转换为基础变量名称
	 * 
	 * @param tableName
	 * @return
	 */
	private String convertBaseName(String tableName) {
		String baseName = tableName.toLowerCase();

		if (StringUtils.isNotBlank(tableToEntityIgnorPrefix)) {
			String ignorPrefix = tableToEntityIgnorPrefix.toLowerCase();
			baseName = StringUtils.substringAfter(baseName, ignorPrefix);
		}

		if (baseName.startsWith(WORD_SEPARATOR)) {
			baseName = StringUtils.substringAfter(baseName, WORD_SEPARATOR);
		}
		while (baseName.contains(WORD_SEPARATOR)) {
			baseName = StringUtils.substringBefore(baseName, WORD_SEPARATOR)
					+ StringUtils.capitalize(StringUtils.substringAfter(baseName, WORD_SEPARATOR));
		}
		return baseName;
	}

	public String getDaoPostfit() {
		return daoPostfit;
	}

	public void setDaoPostfit(String daoPostfit) {
		this.daoPostfit = daoPostfit;
	}

	public String getDaoImplPostfit() {
		return daoImplPostfit;
	}

	public void setDaoImplPostfit(String daoImplPostfit) {
		this.daoImplPostfit = daoImplPostfit;
	}

	public String getDaoTestPostfit() {
		return daoTestPostfit;
	}

	public void setDaoTestPostfit(String daoTestPostfit) {
		this.daoTestPostfit = daoTestPostfit;
	}

	public String getServicePostfit() {
		return servicePostfit;
	}

	public void setServicePostfit(String servicePostfit) {
		this.servicePostfit = servicePostfit;
	}

	public String getServiceImplPostfit() {
		return serviceImplPostfit;
	}

	public void setServiceImplPostfit(String serviceImplPostfit) {
		this.serviceImplPostfit = serviceImplPostfit;
	}

	public String getControllerPostfit() {
		return controllerPostfit;
	}

	public void setControllerPostfit(String controllerPostfit) {
		this.controllerPostfit = controllerPostfit;
	}

	public GenerateConfiguration getGenerateConfiguration() {
		return generateConfiguration;
	}
	public void setGenerateConfiguration(GenerateConfiguration generateConfiguration) {
		this.generateConfiguration = generateConfiguration;
	}
	public String getServiceTestPostfit() {
		return serviceTestPostfit;
	}
	public void setServiceTestPostfit(String serviceTestPostfit) {
		this.serviceTestPostfit = serviceTestPostfit;
	}

	public void setTableToEntityIgnorPrefix(String tableToEntityIgnorPrefix) {
		this.tableToEntityIgnorPrefix = tableToEntityIgnorPrefix;
	}
}
