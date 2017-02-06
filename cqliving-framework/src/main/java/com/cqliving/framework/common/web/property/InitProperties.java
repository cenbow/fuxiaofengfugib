/**
 * Copyright (c) 2009 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.framework.common.web.property;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


/**
 * Title:Spring启动完毕后，将相关业务配置保存到变量 
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author YUWU on 2015年10月19日
 */
public class InitProperties implements ApplicationListener<ContextRefreshedEvent> {
	
	/*private static final Logger logger = LoggerFactory.getLogger(InitProperties.class);*/
	
	private String property;
	
	/**
	 * <p>Description:</p>
	 * @param event
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 * @author YUWU on 2015年10月19日
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			initProperties(event.getApplicationContext());
		}
	}
	
	private void initProperties(ApplicationContext ctx){
		Properties p = (Properties) ctx.getBean(property);
		for(Object key : p.keySet()){
			String k = key.toString();
			ApplicationProperties.addProperty(key.toString(),p.getProperty(k));
		}
	}
	
//	private void autowireing(ApplicationContext ctx){
//		String[] a = ctx.getBeanDefinitionNames();
//		for(String name : a){
//			Object bean = ctx.getBean(name);
//			Class<?> clazz = bean.getClass();
//			Field[] fields = clazz.getDeclaredFields();
//			for(Field field : fields){
//				if(field.isAnnotationPresent(Property.class)){
//					Property p = field.getAnnotation(Property.class);
//					String value = "1211";
//					setField(field, bean, value);
//				}
//			}
//		}
//	}
	
//	private void setField(Field field ,Object bean,String value){
//		Class<?> typeClass = field.getType();  
//		try {
//			Constructor<?> con = typeClass.getConstructor(typeClass);
//			Object val = con.newInstance(value);
//			field.set(bean, val);
//		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			logger.error("参数注入失败",e);
//		}  
//	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
