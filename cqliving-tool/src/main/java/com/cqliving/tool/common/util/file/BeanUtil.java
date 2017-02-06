package com.cqliving.tool.common.util.file;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;

public abstract class BeanUtil {

	private static final Object[] EMPTY_PARAMS = new Object[0];

	public static void copyProperty(Object target, Object source) throws RuntimeException {
		try {
			PropertyDescriptor[] propertys = BeanUtil.getPropertyDescriptors(source.getClass(), null);
			for (PropertyDescriptor property : propertys) {
				String key = property.getName();
				Method getter = property.getReadMethod();
				Object value = getter.invoke(source, EMPTY_PARAMS);
				PropertyDescriptor targetProperty = BeanUtil.getPropertyDescriptor(key, target.getClass());
				if (value != null && value.toString().length() > 0 && targetProperty != null) {
					Method m = targetProperty.getWriteMethod();
					m.invoke(target, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(clazz);
		List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if ("class".equals(key) || (ignoreList != null && ignoreList.contains(key))) {
				continue;
			}
			propertys.add(property);
		}
		return propertys.toArray(new PropertyDescriptor[propertys.size()]);
	}

	public static PropertyDescriptor getPropertyDescriptor(String property, Class<?> clazz) {
		PropertyDescriptor[] propertyDescriptors = BeanUtil.getPropertyDescriptors(clazz);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor.getName().equals(property)) {
				return propertyDescriptor;
			}
		}
		return null;
	}
	  public static Object convertMap(Class<?> type, Map<?, ?> map)
	            throws IntrospectionException, IllegalAccessException,
	            InstantiationException, InvocationTargetException {
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
	        Object obj = type.newInstance(); // 创建 JavaBean 对象
	        // 给 JavaBean 对象的属性赋值
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
	        for (int i = 0; i< propertyDescriptors.length; i++) {
	            PropertyDescriptor descriptor = propertyDescriptors[i];
	            String propertyName = descriptor.getName();
	            Class<?> fieldType = descriptor.getPropertyType();
	            if(!propertyName.equals("class")){
	            	if (map.containsKey(propertyName)) {
	            		Object value = map.get(propertyName);
	            		if(null != value && value instanceof Integer && fieldType == Long.class) {
	            			value = Long.valueOf(value.toString());
	    	            }
	            		if(null != value && value instanceof String && fieldType == Date.class) {
	            			value = DateUtil.parseDateTime(value.toString());
	    	            }
	            		Object[] args = new Object[]{value};
	            		try {
	            			descriptor.getWriteMethod().invoke(obj, args);
	            		} catch (Exception e) {
	            			e.printStackTrace();
	            		}
	            	}
	            }
	        }
	        return obj;
	    }
}
