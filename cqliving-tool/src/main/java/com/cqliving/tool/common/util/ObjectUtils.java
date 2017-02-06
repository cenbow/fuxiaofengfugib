package com.cqliving.tool.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

import com.google.common.collect.Lists;

public abstract class ObjectUtils {

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
	public static void beanToStringMap(Object source, Map<String, String> target, String[] ignoreProperties) {
		try {
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(source.getClass(), ignoreProperties);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				Method getter = property.getReadMethod();
				target.put(key, String.valueOf(getter.invoke(source, new Object[0])));
			}
		} catch (Exception e) {
			throw new RuntimeException("转换失败！");
		}
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
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(source.getClass(), ignoreProperties);
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
	 * 
	 * Title:将对象转成int
	 * <p>Description:将对象转成int</p>
	 * @param obj 对象
	 * @param defaultValue 缺省值
	 * @return
	 * @author tangqiang
	 */
	public static int objToInt(Object obj,int defaultValue) {
		int value = defaultValue;
		try{
			value = Integer.parseInt(obj.toString());
		} catch(Exception e) {			
		}
		return value;
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			return beanInfo.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			return new PropertyDescriptor[0];
		}
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz, String[] ignoreProperties) {
		List<PropertyDescriptor> propertys = Lists.newArrayList();
		PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(clazz);
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			boolean ignore = (ignoreProperties != null && Arrays.binarySearch(ignoreProperties, key) >= 0);
			if ("class".equals(key) || ignore) {
				continue;
			}
			propertys.add(property);
		}
		return propertys.toArray(new PropertyDescriptor[propertys.size()]);
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
			PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(target.getClass(), ignoreProperties);
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
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] object2Byte(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byte2Object(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
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
	 * 检查某个对象是否包含指定属性
	 * @param clazz
	 * @param fieldName
	 * @return {true:包含,false:不包含}
	 */
	public static <T> boolean isContainsFields(Class<T> clazz,String fieldName) {
		Field[] fields=clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if(fields[i].getName().equals(fieldName)){
				return true;
			}
		}
		return false;
	}
	
	private ObjectUtils() {
	}
}
