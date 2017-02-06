package com.cqliving.jpush.test.support;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext-test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class BaseTest {
	//九龙坡
//	protected String appKey = "bd7163b30f3c08f4f644dd55";
//	protected String masterSecret = "ca35d751ce72c5a6e9d702aa";
	//王瑞
	protected String appKey = "a6f5976c3846b0f76ca2a69b";
	protected String masterSecret = "051e3f9f45052d052634244b";
	
	@Test
	public void test() {
		System.out.println("This is BaseTest");
	}

}