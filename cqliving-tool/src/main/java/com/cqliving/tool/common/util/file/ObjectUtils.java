package com.cqliving.tool.common.util.file;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.ConvertUtils;


public abstract class ObjectUtils {
	
	/**
	 * 
	 * Title:创建一个唯一的uuid
	 * <p>Description:创建一个唯一的uuid</p>
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 
	 * Title:将对象转成long
	 * <p>Description:将对象转成long</p>
	 * @param obj 对象
	 * @param defaultValue 缺省值
	 * @return
	 * @author zhuyf
	 */
	public static long objToLong(Object obj,long defaultValue) {
		long value = defaultValue;
		try{
			value = Long.parseLong(obj.toString());
		} catch(Exception e) {			
		}
		return value;
	}
	
	/**
	 * 
	 * Title:将对象转成int
	 * <p>Description:将对象转成int</p>
	 * @param obj 对象
	 * @param defaultValue 缺省值
	 * @return
	 * @author zhuyf
	 */
	public static int objToInt(Object obj,int defaultValue) {
		int value = defaultValue;
		try{
			value = Integer.parseInt(obj.toString());
		} catch(Exception e) {			
		}
		return value;
	}
	
	/**
	 * 
	 * Title:将对象转成byte
	 * <p>Description:将对象转成byte</p>
	 * @param obj 对象
	 * @param defaultValue 缺省值
	 * @return
	 * @author zhuyf
	 */
	public static byte objToByte(Object obj,int defaultValue) {
		byte value = (byte)defaultValue;
		try{
			value = Byte.parseByte(obj.toString());
		} catch(Exception e) {			
		}
		return value;
	}
	
	/**
	 * 
	 * Title:获取对象中，指定字段的值
	 * <p>Description:获取对象中，指定字段的值</p>
	 * @param obj 对象
	 * @param f 字段对象
	 * @return Object
	 * @author zhuyf
	 */
	public static Object getFieldValue(Object obj,Field f) {
		Object retValue = null;
		f.setAccessible(true);
		try {
			retValue = f.get(obj);
		} catch(Exception e) {
			retValue = null;
		}
		return retValue;
	}
	
	/**
	 * 
	 * Title:根据类字段名称获取field
	 * <p>Description:根据类字段名称获取field</p>
	 * @param c 类class
	 * @param name 字段名称
	 * @return Field
	 * @author zhuyf
	 */
	public static Field getFieldByName(Class<?> c,String name) {
		Field f = null;
		try{
			f = c.getDeclaredField(name);
		} catch(Exception e) {
			f = null;
		}
		return f;
	}
	
	/**
	 * 
	 * Title:判断对象，集合，map，字符串是否为空，为空返回true
	 * <p>Description:判断对象，集合，map，字符串是否为空，为空返回true</p>
	 * @param obj
	 * @return
	 * @author zhuyf
	 */
	public static boolean isEmpty(Object obj) {
		if(obj == null) return true;
		if((obj instanceof String) && obj.toString().trim().equals("")) return true;
		if((obj instanceof Collection<?>) && ((Collection<?>)obj).size() < 1) return true;
		if((obj instanceof Map<?,?>) && ((Map<?,?>)obj).size() < 1) return true;
		return false;
	}

	/**
	 * 将javaBean对象转化成map对象，并返回
	 * 
	 * @param source
	 *            - javaBean对象
	 * @return 返回map对象
	 */
	public static Map<String, Object> beanToMap(Object source) {
		Map<String, Object> target = new HashMap<String, Object>();
		beanToMap(source, target);
		return target;
	}

	/**
	 * 将javaBean对象转化成map对象，排除指定key，并返回
	 * 
	 * @param source
	 *            - javaBean对象
	 * @param ignoreProperties
	 *            - 过滤属性
	 * @return 返回map对象
	 */
	public static Map<String, Object> beanToMap(Object source, String[] ignoreProperties) {
		Map<String, Object> target = new HashMap<String, Object>();
		beanToMap(source, target, ignoreProperties);
		return target;
	}

	/**
	 * Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param source
	 *            - javaBean对象
	 * @param target
	 *            - map对象
	 * @param ignoreProperties
	 *            - 过滤属性
	 */
	public static void beanToMap(Object source, Map<String, Object> target, String[] ignoreProperties) {
		try {
			Class<?> clazz = source.getClass();
			PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(clazz, ignoreProperties);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				Method getter = property.getReadMethod();
				target.put(key, getter.invoke(source, new Object[0]));
			}
		} catch (Exception e) {
			throw new RuntimeException("转换失败！");
		}
	}

	/**
	 * 将javaBean对象转化成map对象
	 * 
	 * @param source
	 *            - javaBean对象
	 * @param target
	 *            - map对象
	 */
	public static void beanToMap(Object source, Map<String, Object> target) {
		beanToMap(source, target, null);
	}

	/**
	 * 将map对象转化成javaBean对象
	 * 
	 * @param source
	 *            - map对象
	 * @param target
	 *            - javaBean对象
	 * @param ignoreProperties
	 *            - 过滤属性
	 */
	public static void mapToBean(Map<String, Object> source, Object target, String[] ignoreProperties) {
		try {
			Class<?> clazz = target.getClass();
			PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(clazz, ignoreProperties);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!source.containsKey(key))
					continue;
				Object value = source.get(key);
				Method setter = property.getWriteMethod();
				value = ConvertUtils.convert(value, property.getPropertyType());
				setter.invoke(target, value);
			}
		} catch (Exception e) {
			throw new RuntimeException("转换失败！", e);
		}
	}

	/**
	 * 将map对象转化成javaBean对象
	 * 
	 * @param source
	 *            - map对象
	 * @param target
	 *            - javaBean对象
	 */
	public static void mapToBean(Map<String, Object> source, Object target) {
		mapToBean(source, target, null);
	}

	private ObjectUtils() {		
	}
	
	
}
