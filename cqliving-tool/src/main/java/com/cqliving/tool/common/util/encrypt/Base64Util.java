package com.cqliving.tool.common.util.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.springframework.util.Base64Utils;

import com.cqliving.framework.utils.StringUtil;
import com.cqliving.tool.common.util.file.FileUtils;


/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2013-2016
 * @author fuxiaofeng on 2015年5月21日
 */
public class Base64Util {

	
	/**
	 * <p>Description:解码base64图片字符串为文件</p>
	 * @param base64imge
	 * @param fileName  解码base64生成的文件名
	 * @param fileLocalPath 解码base64生成的文件的路径
	 * @return
	 * @author fuxiaofeng on 2015年5月21日
	 */
	public static File decodeBase64(String base64imge,String fileName,String fileLocalPath) {
		byte[] bytes = Base64Utils.decodeFromString(base64imge);//默认UTF-8解码
		if(null == bytes || bytes.length <=0){
			return null;
		}
		File file = new File(fileLocalPath+fileName);
		
		if(!file.exists()){
			FileUtils.createNewFile(file);
		}
		FileOutputStream fos =null;
		try {
			fos = new FileOutputStream(file);
			fos.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeStream(null,fos);
		}
		return file;
	}

	
	/**
	 * <p>Description:将文件转化为base64字符串</p>
	 * @param file  文件
	 * @return
	 * @author fuxiaofeng on 2015年5月21日
	 */
	public static String encodeBase64(File file) {
		String str = "";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			int len = fis.available();
			byte[] by = new byte[len];
			fis.read(by);
			str = Base64Utils.encodeToString(by);//默认为UTF-8编码
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			closeStream(fis,null);
		}
		return str;
	}
	
	public static String encodeBase64(String str){
		
		if(StringUtil.isEmpty(str))return "";
		
		try {
			return Base64Utils.encodeToString(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String decodeBase64(String base64Str){
		
		if(StringUtil.isEmpty(base64Str))return "";
		return new String(Base64Utils.decodeFromString(base64Str));
		
	}
	
	/**
	 * @param fis
	 * @param fos
	 * 关闭流
	 */
	public static void closeStream(InputStream is,OutputStream os){
		try {
			if(null != is){
				is.close();
			}
			if(null != os){
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		File file = new File("C:\\Users\\fuxiaofeng\\Desktop\\123.png");
		String str = encodeBase64(file);
		file = decodeBase64("","么.png","d:/");
		System.out.println(file.exists());
		System.out.println(str.length());
	}
}
