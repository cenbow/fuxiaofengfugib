package com.cqliving.framework.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkers {

	/**
	 * 渲染模板字符串。
	 */
	public static String rendereString(String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString), new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 渲染Template文件.
	 */
	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static String renderTemplate(Template template, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * 创建默认配置，设定模板目录.
	 */
	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration();
		
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}

	public static void main(String[] args) throws Exception {

		String path = "file:/D:/workshop/cqliving/code-generator";
		URL uri = new URL(path);
		System.out.println(uri.toString());
		System.out.println(uri.getPath());
//		Configuration cfg = FreeMarkers
//				.buildConfiguration("file:///D:\\workshop\\CQLIVING\\code-generator\\src\\main\\resources\\template");
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("tableName", "SYS_USER");
//		root.put("domainName", "User");
//		String result = renderTemplate(cfg.getTemplate("domain.ftl"), root);
//		System.out.println(result);

	}
}
