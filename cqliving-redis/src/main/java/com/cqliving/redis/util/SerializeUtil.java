package com.cqliving.redis.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化工具类 Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2013
 * 
 * @author Administrator on 2014年12月29日
 */
public abstract class SerializeUtil {
	private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 序列化 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error("serialize fail!", e);
		}
		return null;
	}

	/**
	 * 反序列化 Title:
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			logger.error("unserialize fail!", e);
		}
		return null;
	}
}
