package com.cqliving.codegenerator.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqliving.codegenerator.CodeGeneratorFactory;

/**
 * asdfs
 * 
 * Date : 2012-12-12
 * 
 * @author zhangpu
 * 
 */

public class CodeGeneratorMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-main.xml");
		CodeGeneratorFactory codeGeneratorFactory = (CodeGeneratorFactory) context.getBean("codeGeneratorFactory");
		String dbUrl = "jdbc:mysql://localhost:3306/cqliving_online?useUnicode=true&characterEncoding=utf-8";
		String database = dbUrl.substring(dbUrl.lastIndexOf("/")+1, dbUrl.indexOf("?"));
		//codeGeneratorFactory.generateTables(database,,,,,,);
		codeGeneratorFactory.generateTables(database,"SMS_LOG","SMS_STATUS");
	}

}
