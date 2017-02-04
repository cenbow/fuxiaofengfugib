/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2016
 * 
 * @author fuxiaofeng on 2016年9月2日
 */
public class Crypto {

	private final static String KEY_ALGORITHM="RSA";
	private final static String CIPHER_ALGORITHM = "RSA";
	private final static String CODE_TYPE = "UTF-8";
	// 非对称加密
	public static String encodeRSA(String str, String publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			PublicKey pk = Crypto.restorePublicKey(publicKey);
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			// 产生钥匙对
			/*
			 * KeyPairGenerator
			 * keyPairGenerator=KeyPairGenerator.getInstance("RSA"); KeyPair
			 * keyPair=keyPairGenerator.generateKeyPair(); PublicKey publicKey=
			 * keyPair.getPublic(); PrivateKey privateKey=keyPair.getPrivate();
			 */
			// 前面做加密时都是用默认的编码，如果加密的数据是中文会出现乱码，现在改成中文的数据进行测试以下
			int mxBlockSize = publicKey.length()/8;
			String[] arrStr = Crypto.splitByLength(str,mxBlockSize-11).toArray(new String[]{});
			
			byte[] allResults = new byte[]{};
			for(String substr : arrStr){
				byte[] results = cipher.doFinal(substr.getBytes(CODE_TYPE));
				allResults = Crypto.concat(allResults,results);
			}
			return DatatypeConverter.printBase64Binary(allResults);
			//return new String(results,CODE_TYPE);
		} catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException
				| IOException | InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范
	 * 
	 * @param keyBytes
	 * @return
	 */
	public static PublicKey restorePublicKey(String publickKey) {
		
		try {
			byte[] keyBytes = DatatypeConverter.parseBase64Binary(publickKey);
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> splitByLength(String str,int splitLength){
		
		if(StringUtil.isEmpty(str))return null;
		List<String> list = Lists.newArrayList();
		if(str.length() <= splitLength){
			list.add(str);
			return list;
		}
		int a = str.length() / splitLength;
		for(int i=0;i<a;i++){
			int beginIndex = splitLength*i;
			String subStr = str.substring(beginIndex,beginIndex+splitLength);
			list.add(subStr);
		}
		int b = str.length() % splitLength;
		if(b>=1){
			String subStr = str.substring(splitLength*a);
			list.add(subStr);
		}
		return list;
	}
	
	//数组合并
	public static byte[] concat(byte[] arr1,byte[] arr2){
		
		byte[] newarr = new byte[arr1.length+arr2.length];
		System.arraycopy(arr1, 0,newarr, 0,arr1.length);  
		System.arraycopy(arr2, 0,newarr,arr1.length,arr2.length);  
		return newarr;
	}
	
    public static void main(String[] args) {
		
    	//Provider[] vi = Security.getProviders();
    	/*for(Provider p : vi){
    		for(Service s : p.getServices()){
    			System.out.println(s.getAlgorithm());
    		}
    	}*/
    	//String s = Crypto.encodeRSA("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCi4KQZzhKwZXrm3dSZwl34LSgHLc250Ch8BZDIQNr6FrOT/PxdQCotwrP3TfjTKSTRov+3A6GMztRvLWjpZK++MzNBmHUNObAfdSTGLbXZQwg0niXeef4C8ELINpF67uTcFNORcZ4az7AU4MgUNEYRD7JD83CP/tpPG+LFnLou9wIDAQAB");
    	//System.out.println(s);
    	String str = "恭喜您获得（礼品名称）一份，请于9月10日10点至21点凭兑换密码{convert_code}到砂之船（南京）奥特莱斯2F客服台兑换奖品。兑换密码请妥善保存，逾期将视为放弃。商场地址：江宁区秣周东路18号【砂之船奥莱】";
    	List<String> list = Crypto.splitByLength(str,19);
    	
    	byte[] b1 =new  byte[]{1,2,3};
    	byte[] b2 =new byte[]{4,5,6};
    	System.out.println(Arrays.toString(Crypto.concat(b1, b2)));
    	
	}
}
