package com.cqliving.tool.utils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Title:XML相关工具类
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年11月4日
 */
public class XmlUtils {
	
	public static boolean isXml(String content){
//		return content.indexOf("type='text/xsl'") > 0;
	    return true;
	}
	
	/**
	 * Title:把xml中的xpathExpression节点返回对象
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月4日
	 * @param xml
	 * @param xpathExpression 如：/rss/channel/item
	 * @param class
	 * @return
	 * @throws DocumentException 
	 */
	@SuppressWarnings({ "unchecked"})
	public static <T> List<T> readXmlToObjects(String xml, String xpathExpression, Class<T> clazz) throws DocumentException{
		if(!isXml(xml)){
			return null;
		}
		List<T> rs = Lists.newArrayList();
		Document doc = DocumentHelper.parseText(xml);
		List<Element> list = doc.selectNodes(xpathExpression);
		T t;
		for(Element e : list){
			t = toBean(e, clazz);
			if(t != null)
				rs.add(t);
		}
		return rs;
	}
	
	/**
	 * Title:把xml转成对象
	 * <p>Description:
	 * 如在传入的对象中存在XMLCONTENT成员变量，那么会把当前节点的xml代码set进去。
	 * </p>
	 * @author DeweiLi on 2016年11月3日
	 * @param xml
	 * @param cls
	 * @return
	 */
	private static <T> T toBean(Element el, Class<T> clazz){
		Field[] properties = clazz.getDeclaredFields();//获得实体类的所有属性
		T t = null;
		Element element;
		try {
			t = clazz.newInstance();
			for(Field field : properties ){
				element = el.element(field.getName());
				if(element != null){//对象成员在xml中是否存在
					if("source".equals(field.getName())){//由于第三方给的数据不是标准的xml格式<![CDATA[>这个存放，所以这里只能单独处理下。
						BeanUtils.setProperty(t, field.getName(), element.getStringValue());
					}else{
						BeanUtils.setProperty(t, field.getName(), element.getText());
					}
				}
			}
			//返回当前节点的整个xml，如果所传的对象中存在XMLCONTENT变量
			try {
				Field field = clazz.getDeclaredField("XMLCONTENT");
				BeanUtils.setProperty(t, field.getName(), el.asXML());
			} catch (NoSuchFieldException | SecurityException e) {
				//没有这XMLCONTENT这个属性就不处理
			}
		} catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	/**
	 * Title:匹配字符串中img标签的src
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月8日
	 * @param content
	 * @return
	 */
	public static List<String> getImgSrc(String content) {
		List<String> list = Lists.newArrayList();
		Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
		Matcher m_img = p_img.matcher(content);
		boolean result_img = m_img.find();
		if (result_img) {
			while (result_img) {
				String str_img = m_img.group(2);
				Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
				Matcher m_src = p_src.matcher(str_img);
				if (m_src.find()) {
					String str_src = m_src.group(3);
					list.add(str_src);
				}
				result_img = m_img.find();
			}
		}
		return list;
	}
	/**
	 * Title:根据类容解析img标签的属性
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月9日
	 * @param content
	 * @param attributes 默认为src,多个用逗号隔开
	 * @return
	 */
	public static List<Map<String, String>> getImgAttribute(String content, String attributes) {
		if(attributes == null && "".equals(attributes)){
			attributes = "src";
		}
		String[] attrs = attributes.split(",");
		Map<String, String> map;
		List<Map<String, String>> list = Lists.newArrayList();
		Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
		Matcher m_img = p_img.matcher(content);
		boolean result_img = m_img.find();
		String str_img;
		Pattern pat;
		Matcher mat;
		if (result_img) {
			while (result_img) {
				str_img = m_img.group(2);
				map = Maps.newHashMap();
				for(String str: attrs){
					pat = Pattern.compile("("+str.toLowerCase()+"|"+str.toUpperCase()+")=(\"|\')(.*?)(\"|\')");
					mat = pat.matcher(str_img);
					if (mat.find()) {
						map.put(str, mat.group(3));
					}
				}
				list.add(map);
				result_img = m_img.find();
			}
		}
		return list;
	}
	
	/**
	 * Title:重庆app抓稿，多媒体视频格式转换
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月8日
	 * @param media
	 * @return
	 */
	public static String getMedia(String media){
		if(media == null && "".equals(media))
			return null;
		if(media.startsWith("ld"))
			media = media.replaceFirst("ld", "http");
		if(media.indexOf("/vod") != -1)
			media = media.replaceFirst("/vod", "");
		return media;
	}
}
