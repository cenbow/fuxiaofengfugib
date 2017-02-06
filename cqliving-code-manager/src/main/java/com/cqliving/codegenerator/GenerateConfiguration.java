package com.cqliving.codegenerator;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GenerateConfiguration {

    /*******************************/
    /********   service 设置   *********/
    /*******************************/
	/** 工作目录 */
	private String workspace;
	/** 目标根package */
	private String rootPackage;

	private String codePath = "java";

	private String resourcePath = "resources";

	private String testPath = "test";

    /*******************************/
    /********   模板设置   *********/
    /*******************************/
	private String templatePath;

    /*******************************/
    /********   生成选项   *********/
    /*******************************/
    private String generateOptions;

    /*******************************/
    /********web 工程设置***********/
    /*******************************/

    //web工程目录
    private String webWorkspace;

    //controller  包名
    private String webPackage;

    private String webappPath = "webapp";

    /** jsp 文件路径 */
    private String pagePath = "/admin/";

    /** url 访问路径 */
    private String pageMapping;

    /** tiles 前缀 */
    private String tilesName;

    /** jsp 前缀 */
    private String pagePrefix;

    /** 查询条件字段 */
    private String searchColumns;
    /** 列表显示字段 */
    private String listColumns;
    /** 增加修改字段 */
    private String detailColumns;
    /** 详细显示字段 */
    private String viewColumns;

    public List<String> getSearchColumnsList() {
        return toList(searchColumns);
    }

    public List<String> getListColumnsList() {
        return toList(listColumns);
    }

    public List<String> getDetailColumnsList() {
        return toList(detailColumns);
    }

    public List<String> getViewColumnsList() {
        return toList(viewColumns);
    }

    private List<String> toList(String str){
        List<String> strList = new ArrayList<String>();
        if(StringUtils.isNotBlank(str)){
            String[] strs = str.split(",");
            for(String s : strs){
                if(StringUtils.isNotBlank(s)){
                    strList.add(s.trim().toLowerCase());
                }
            }
        }
        return strList;
    }

	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}

	public String getCodePath() {
		return codePath;
	}

	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getWebappPath() {
		return webappPath;
	}

	public void setWebappPath(String webappPath) {
		this.webappPath = webappPath;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getTestPath() {
		return testPath;
	}

	public void setTestPath(String testPath) {
		this.testPath = testPath;
	}

    public String getTilesName() {
        return tilesName;
    }

    public void setTilesName(String tilesName) {
        this.tilesName = tilesName;
    }

    public String getSearchColumns() {
        return searchColumns;
    }

    public void setSearchColumns(String searchColumns) {
        this.searchColumns = searchColumns;
    }

    public String getListColumns() {
        return listColumns;
    }

    public void setListColumns(String listColumns) {
        this.listColumns = listColumns;
    }

    public String getDetailColumns() {
        return detailColumns;
    }

    public void setDetailColumns(String detailColumns) {
        this.detailColumns = detailColumns;
    }

    public String getViewColumns() {
        return viewColumns;
    }

    public void setViewColumns(String viewColumns) {
        this.viewColumns = viewColumns;
    }

    public String getPagePrefix() {
        return pagePrefix;
    }

    public void setPagePrefix(String pagePrefix) {
        this.pagePrefix = pagePrefix;
    }

    public String getWebWorkspace() {
        return webWorkspace;
    }

    public void setWebWorkspace(String webWorkspace) {
        this.webWorkspace = webWorkspace;
    }

    public String getWebPackage() {
        return webPackage;
    }

    public void setWebPackage(String webPackage) {
        this.webPackage = webPackage;
    }

    public String getPageMapping() {
        return pageMapping;
    }

    public void setPageMapping(String pageMapping) {
        this.pageMapping = pageMapping;
    }

    public String getGenerateOptions() {
        return generateOptions;
    }

    public void setGenerateOptions(String generateOptions) {
        this.generateOptions = generateOptions;
    }

    public boolean isGenerate(String beanName){
        if(StringUtils.isBlank(generateOptions)){
            return true;
        }
        String moduleName="";
        if("domainModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="domain";
        } else if("jpaDaoModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="dao";
        } else if("managerInterfaceModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="manager";
        } else if("managerImplModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="manager";
        } else if("serviceInterfaceModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="service";
        } else if("serviceImplModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="service";
        } else if("managerControllerModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="controller";
        } else if("pagesModuleGenerator".equalsIgnoreCase(beanName)){
            moduleName="page";
        }

        return toList(generateOptions).contains(moduleName);
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
