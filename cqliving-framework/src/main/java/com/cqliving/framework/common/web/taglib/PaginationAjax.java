package com.cqliving.framework.common.web.taglib;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * com.CQLIVING.framework.common.web.taglib.
 * User: wangyx
 * Date: 14-12-11
 * Time: 下午2:14
 */
public class PaginationAjax extends TagSupport {
	private static final long serialVersionUID = 6245615313095301345L;

	//初始化参数
    private static Properties properties=new Properties();
    static {
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    private static VelocityEngine velocityEngine=new VelocityEngine(properties);

    /** 上下文中分页对象名称 */
    private String property = "pageInfo";

    /**页码显示长度*/
    private int pageLength=10;
    
    /** 加载数据URL */
    private String dataUrl = "";
    /** 提交参数的formId */
    private String paramFormId = "";
    //load数据
    private String loadId = "";
    private String pageNoFormName="pageNo";

    //private String pageSizeFormName="pageSize";
    /**
	 * 
	 */
	private static final Map<Object, Object> PAGING_OPTIONS;
	static {
		PAGING_OPTIONS = new LinkedHashMap<Object, Object>();
		PAGING_OPTIONS.put("10", "10");
		PAGING_OPTIONS.put("20", "20");
		PAGING_OPTIONS.put("50", "50");
		PAGING_OPTIONS.put("100", "100");
	}
	
    @SuppressWarnings("unchecked")
	@Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        //JspWriter out = this.pageContext.getOut();
        PageInfo<T> pageInfo = (PageInfo<T>) request.getAttribute(property);
        
        if(null == pageInfo)return SKIP_BODY;
        
        //计算显示的最大页数
        int pageEnd = 1;
        if(pageInfo.getTotalPage()>=1) {
            pageEnd = pageInfo.getCurrentPage() + (pageLength % 2 == 0 ?  pageLength / 2 - 1 : pageLength / 2);
            pageEnd = pageEnd <= pageLength ? pageLength : pageEnd;
            pageEnd = pageEnd >= pageInfo.getTotalPage() ? Integer.parseInt(pageInfo.getTotalPage()+"") : pageEnd;
        }
        //开始页码数
        int pageStart = 1;
        if(pageEnd>=2){
            pageStart = pageEnd - pageLength + 1 <= 0 ? 1 : pageEnd - pageLength + 1;
        }

        String query = request.getQueryString();
        if(StringUtils.isNotBlank(query)){
            int pcLength = query.indexOf(pageNoFormName);
            if(pcLength!=-1){
                query = query.substring(0, pcLength);
            }else{
                query+="&";
            }
        }
        query=(query==null ? "?" : "?"+query);

        
        VelocityContext context = new VelocityContext();
        context.put("pageUrl", query);
        context.put("pageStart", pageStart);
        context.put("pageEnd", pageEnd);
        context.put("currentPage", pageInfo.getCurrentPage());
        context.put("pageSize", pageInfo.getCountOfCurrentPage());
        context.put("totalPage", pageInfo.getTotalPage());
        
        context.put("pagingOptions", PAGING_OPTIONS);
        context.put("totalCount", pageInfo.getTotalCount());//记条数
        
        Integer pageStartNumTemp = (pageInfo.getCurrentPage() - 1) * pageInfo.getCountOfCurrentPage() + 1;//当页结束条数
        Long pageStartNum = pageInfo.getTotalCount() == 0L ? 0L : pageStartNumTemp;
        context.put("pageStartNum", pageStartNum);//当页开始条数
        
        Integer pageEndNumTemp = pageInfo.getCurrentPage() * pageInfo.getCountOfCurrentPage();//当页结束条数
        Long pageEndNum = Long.parseLong((pageEndNumTemp.toString())) > pageInfo.getTotalCount() ? pageInfo.getTotalCount() : pageEndNumTemp;
        context.put("pageEndNum", pageEndNum);//当页结束条数
        
        context.put("dataUrl", dataUrl);//当页结束条数
        context.put("paramFormId", paramFormId);//当页结束条数
        context.put("loadId", loadId);
        try {
            velocityEngine.mergeTemplate("/vm/paginationAjax.vm", "utf-8", context, pageContext.getOut());
        } catch (Exception e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return SKIP_BODY;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getParamFormId() {
		return paramFormId;
	}

	public void setParamFormId(String paramFormId) {
		this.paramFormId = paramFormId;
	}

	public String getLoadId() {
		return loadId;
	}

	public void setLoadId(String loadId) {
		this.loadId = loadId;
	}
    
}
