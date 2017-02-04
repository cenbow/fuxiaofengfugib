/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.jywth.common;

import java.security.MessageDigest;

import com.cqliving.tool.common.util.encrypt.CryptUnit;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月5日
 */
public class MD5Util {

	public static String MD5(String para_Source,String encodeType) {
		String enStr = null;
		if (para_Source == null)
			return null;
		if (para_Source.equalsIgnoreCase(""))
			return new String("");
		try {
			byte result[] = MD5A(para_Source.getBytes(encodeType));
			enStr = new String(result,encodeType);
		} catch (Exception E) {
			return null;
		}
		enStr = CryptUnit.Byte2Hex(enStr).toUpperCase();
		return enStr;
	}
	
	private static byte[] MD5A(byte[] input) {
		byte[] digest = null;
		try {
			java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5"); // or "SHA-1"
			alg.update(input);
			digest = alg.digest();
		} catch (Exception E) {
			return null;
		}
		return digest;
	}
	
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
