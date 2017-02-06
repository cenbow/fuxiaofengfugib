package com.cqliving.getui.push.test.support;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年12月14日
 */
@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/applicationContext-test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})
public class BaseTest {
	
	//唐涛
	protected String appId = "EcXYF3ilcS8EN1Ltp6fJJ7";
	protected String appKey = "OJbOYnQhF19OhoW8n8jRy5";
	protected String masterSecret = "X2wJuRxq4CAfZ4Xe3PclSA";
	
	@Test
	public void test() {
		System.out.println("This is BaseTest");
	}

}