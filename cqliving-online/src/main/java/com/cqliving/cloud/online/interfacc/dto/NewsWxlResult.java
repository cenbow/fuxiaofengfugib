/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
/**
 * 
 */
package com.cqliving.cloud.online.interfacc.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) feinno 2013-2016
 * @author Tangtao on 2016年5月2日
 */
public class NewsWxlResult {
	
	/** 资讯集合 */
	private List<NewsWxlData> news;
	/** 轮播集合 */
	private List<NewsData> carousels;
	
	public List<NewsWxlData> getNews() {
		return news;
	}

	public void setNews(List<NewsWxlData> news) {
		this.news = news;
	}

	public List<NewsData> getCarousels() {
		return carousels;
	}

	public void setCarousels(List<NewsData> carousels) {
		this.carousels = carousels;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}