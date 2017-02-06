package com.cqliving.cloud.taglib;

import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * com.CQLIVING.framework.common.web.taglib.
 * User: wangyx
 * Date: 14-12-11
 * Time: 下午2:14
 */
public class FileUpload extends TagSupport {
	private static final long serialVersionUID = 5479064974190062578L;

	//初始化参数
    private static Properties properties=new Properties();
    static {
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
    }

    private static VelocityEngine velocityEngine=new VelocityEngine(properties);

    /** 名称 */
    private String name;

    /** 图片url */
    private String url;


    @Override
    public int doStartTag() throws JspException {
        VelocityContext context = new VelocityContext();
        context.put("name", name);
        context.put("imgUrl", url);
        try {
            velocityEngine.mergeTemplate("/vm/file_upload.vm", "utf-8", context, pageContext.getOut());
        } catch (Exception e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return SKIP_BODY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
