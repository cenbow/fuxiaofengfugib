package com.cqliving.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurableConstants {
	protected static Logger logger = LoggerFactory.getLogger(ConfigurableConstants.class);
	protected static Properties p = new Properties();

	protected static void init(String propertyFileName) {
		InputStream in = null;
		//load x.default.property added by wanghailong on 2013-6-05
		try {
			in = ConfigurableConstants.class.getClassLoader().getResourceAsStream(getDefaultPropertyFileName(propertyFileName));
			if (in != null)
				p.load(in);
		} catch (Exception e) {
			logger.error("load " + propertyFileName + " into Constants error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
			}
		}
		
		try {
			in = ConfigurableConstants.class.getClassLoader().getResourceAsStream(propertyFileName);
			if (in != null)
				p.load(in);
		} catch (IOException e) {
			logger.error("load " + propertyFileName + " into Constants error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
			}
		}
	}
	
	protected static String getProperty(String key, String defaultValue) {
		return p.getProperty(key, defaultValue);
	}
	
	protected static String getProperty(String key) {
		return p.getProperty(key);
	}
	
	protected static String getDefaultPropertyFileName(String propertyFileName){
		StringBuffer ns = new StringBuffer();		
		if(propertyFileName!=null && propertyFileName.endsWith(".properties")){
			ns.append(propertyFileName.substring(0, propertyFileName.indexOf(".properties")));
			ns.append(".default.properties");
		}
		return ns.toString();
	}
}
