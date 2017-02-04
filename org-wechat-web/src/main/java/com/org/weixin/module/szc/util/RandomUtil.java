/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.org.weixin.module.szc.util;

import java.util.Random;

import com.cqliving.tool.common.util.StringUtil;


/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author fuxiaofeng on 2016年4月3日
 */
public class RandomUtil {

	/**
	 * Title:
	 * <p>Description://以时间戳为元的随机数，不严谨，仅适用于小量数据</p>
	 * @author fuxiaofeng on 2016年9月4日
	 * @param length
	 * @return
	 */
	public static String random(int length){
		long mili = System.currentTimeMillis();
		String str = String.valueOf(mili);
		str = str.substring(str.length()-length);
		return str;
	}
	
	public static String randomR(int length){
		
		Random r = new Random();
		while(true){
			String i = String.valueOf(r.nextInt());
			i = i.replace("-","");
			if(i.length() == length){
				return i;
			}
			i = String.valueOf(r.nextInt());
		}
	}
	
	/**
	 * Title:
	 * <p>Description:比较严谨，测试1000000数据，取5的长度只有1条到2条重复,8以上长度不会重复</p>
	 * @author fuxiaofeng on 2016年9月4日
	 * @param length
	 * @return
	 */
	public static String randomUUid(int length){
		
		String uuid = StringUtil.getUUID().toUpperCase();
		if(length>= uuid.length())return uuid;
		if(length<uuid.length())uuid = uuid.substring(0, length);
		return uuid;
	}
	
	//重给定数中随机抽取一个
	public static int getRandom(int random){
		Random r = new Random();
		return r.nextInt(random);
	}
	
	public static void main(String[] args) {
		
		String r = "";
		for(int i=0;i<1000000;i++){
			String n = RandomUtil.randomR(8);
			if(n.equals(r)){
				System.out.println(n);
			}
			r = n;
		}
	}
}
