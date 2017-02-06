package com.cqliving.tool.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wanghailong 
 */
public class Regexs {
	
	public static String extract(String regExp,String content)
	{
        Pattern p=Pattern.compile(regExp);
        Matcher m=p.matcher(content);
        if(m.find())
        {
            return m.group();
        }
        else 
        {
        	return null;
        }
	}

	public static  boolean matcher(String regExp,String content)
	{
		 Pattern p=Pattern.compile(regExp);
         Matcher m=p.matcher(content);
         return m.matches();
	}
	
	public static Integer extractInteger(String content,String prefix)
	{
		String numberString = extractNumber(content,prefix);
		if(numberString == null)
		{
			return null;
		}
		
		return Integer.valueOf(numberString);
	}
	
	public static Long extractLong(String content,String prefix)
	{
		String numberString = extractNumber(content,prefix);
		if(numberString == null)
		{
			return null;
		}
		
		return Long.valueOf(numberString);
	}
	
	private static String extractNumber(String content,String prefix)
	{
		if(content ==null)
		{
			return null;
		}
		try
		{
			if(prefix!=null){
				return Regexs.extract(prefix+"\\s*\\d+", content).replaceAll(prefix, "").trim();
			}else{
				return Regexs.extract("\\d+", content);
			}
			
		} catch (Exception e) {
			return null;
		}
	}
}
