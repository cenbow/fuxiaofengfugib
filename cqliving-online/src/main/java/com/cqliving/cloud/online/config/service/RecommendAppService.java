package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.tool.common.Response;

/**
 * 区县推荐APP表，在重庆APP中使用 Service
 * Date: 2016-10-26 17:18:11
 * @author Code Generator
 */
public interface RecommendAppService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<RecommendApp>> queryForPage(PageInfo<RecommendApp> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<RecommendApp> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(RecommendApp domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年10月26日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	Response<Void> modifySortNo(Long id, Integer sortNo);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月26日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<RecommendAppDto>> queryDtoForPage(PageInfo<RecommendAppDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月26日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	Response<ScrollPage<RecommendAppDto>> queryForScrollPage(ScrollPage<RecommendAppDto> scrollPage, Map<String, Object> conditions);
	
}