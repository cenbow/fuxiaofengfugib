package com.cqliving.codegenerator.parser;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NamesHold {

	private String domainClassName;

	private String domainPackage;

	private String daoClassName;

	private String daoPackage;

	private String daoImplClassName;

	private String daoImplPackage;

	private String daoTestPackage;

	private String daoTestClassName;

	private String managerPackage;
	private String managerClassName;
	private String managerImplPackage;
	private String managerImplClassName;
	private String managerTestPackage;
	private String managerTestClassName;
	
	private String servicePackage;

	private String serviceClassName;

	private String serviceImplPackage;
	private String serviceImplClassName;

	
	private String serviceTestPackage;
	private String serviceTestClassName;
	
	private String controllerPackage;
	private String controllerClassName;

	private String pagePath;

	public String getDomainClassName() {
		return domainClassName;
	}

	public void setDomainClassName(String domainClassName) {
		this.domainClassName = domainClassName;
	}

	public String getDaoClassName() {
		return daoClassName;
	}

	public void setDaoClassName(String daoClassName) {
		this.daoClassName = daoClassName;
	}

	public String getDaoImplClassName() {
		return daoImplClassName;
	}

	public void setDaoImplClassName(String daoImplClassName) {
		this.daoImplClassName = daoImplClassName;
	}

	public String getDaoTestClassName() {
		return daoTestClassName;
	}

	public void setDaoTestClassName(String daoTestClassName) {
		this.daoTestClassName = daoTestClassName;
	}

	public String getServiceClassName() {
		return serviceClassName;
	}

	public void setServiceClassName(String serviceClassName) {
		this.serviceClassName = serviceClassName;
	}

	public String getServiceImplClassName() {
		return serviceImplClassName;
	}

	public void setServiceImplClassName(String serviceImplClassName) {
		this.serviceImplClassName = serviceImplClassName;
	}

	public String getServiceTestClassName() {
		return serviceTestClassName;
	}

	public void setServiceTestClassName(String serviceTestClassName) {
		this.serviceTestClassName = serviceTestClassName;
	}

	public String getControllerClassName() {
		return controllerClassName;
	}

	public void setControllerClassName(String controllerClassName) {
		this.controllerClassName = controllerClassName;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getDaoImplPackage() {
		return daoImplPackage;
	}

	public void setDaoImplPackage(String daoImplPackage) {
		this.daoImplPackage = daoImplPackage;
	}

	public String getDaoTestPackage() {
		return daoTestPackage;
	}

	public void setDaoTestPackage(String daoTestPackage) {
		this.daoTestPackage = daoTestPackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getServiceTestPackage() {
		return serviceTestPackage;
	}

	public void setServiceTestPackage(String serviceTestPackage) {
		this.serviceTestPackage = serviceTestPackage;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

    public String getManagerPackage() {
		return managerPackage;
	}

	public void setManagerPackage(String managerPackage) {
		this.managerPackage = managerPackage;
	}

	public String getManagerClassName() {
		return managerClassName;
	}

	public void setManagerClassName(String managerClassName) {
		this.managerClassName = managerClassName;
	}

	public String getManagerImplPackage() {
		return managerImplPackage;
	}

	public void setManagerImplPackage(String managerImplPackage) {
		this.managerImplPackage = managerImplPackage;
	}

	public String getManagerImplClassName() {
		return managerImplClassName;
	}

	public void setManagerImplClassName(String managerImplClassName) {
		this.managerImplClassName = managerImplClassName;
	}

	public String getManagerTestPackage() {
		return managerTestPackage;
	}

	public void setManagerTestPackage(String managerTestPackage) {
		this.managerTestPackage = managerTestPackage;
	}

	public String getManagerTestClassName() {
		return managerTestClassName;
	}

	public void setManagerTestClassName(String managerTestClassName) {
		this.managerTestClassName = managerTestClassName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
