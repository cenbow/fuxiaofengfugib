package com.cqliving.cloud.online.info.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.dto.InfoClassifyDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年4月28日
 */
public interface InfoClassifyDaoCustom {
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年4月28日
	 * @param pageInfo
	 * @param map
	 * @param sortMap
	 * @return
	 */
	PageInfo<InfoClassifyDto> queryDtoForPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> map, Map<String, Boolean> sortMap);
	
	/**
	 * Title:
	 * @author Tangtao on 2016年5月2日
	 * @param page
	 * @param conditions
	 * @param isCarousel
	 * @return
	 */
	ScrollPage<InfoClassifyDto> queryDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions, Boolean isCarousel);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2017年1月13日
	 * @param page
	 * @param conditions
	 * @param isCarousel
	 * @param onlyWechat
	 * @return
	 */
	ScrollPage<InfoClassifyDto> queryWxlDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions, Boolean isCarousel, boolean onlyWechat);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月6日
	 * @param page
	 * @param conditions
	 * @return
	 */
	ScrollPage<InfoClassifyDto> querySpecialSubDtoForScrollPage(ScrollPage<InfoClassifyDto> page, Map<String, Object> conditions);	
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月4日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<InfoClassifyDto> queryDtoForCopyPage(PageInfo<InfoClassifyDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月11日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<InfoClassifyDto> queryDtoForRecommendPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年5月12日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<InfoClassifyDto> queryDtoForSpecialSubPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 获取推荐新闻</p>
	 * @author Tangtao on 2016年8月18日
	 * @param appId
	 * @return
	 */
	List<InfoClassifyDto> getRecommended(Long appId);	
	
	public PageInfo<InfoClassifyDto> queryInfoClassifyCorrelationPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap);

	public PageInfo<InfoClassifyDto> queryHadCorrInfoClassifyPage(PageInfo<InfoClassifyDto> pageInfo,
			Map<String, Object> searchMap, Map<String, Boolean> orderMap);
	
	public List<Long> findclassifyIds(Long informationId);
	//根据原创内容修改是否推荐到微信小程序
	public void updateIsViewWechat(Long infocontentId,Byte isViewWechat);
}