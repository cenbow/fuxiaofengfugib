package com.cqliving.cloud.online.config.manager;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 区县推荐APP表，在重庆APP中使用 Manager
 * Date: 2016-10-26 17:18:11
 * @author Code Generator
 */
public interface RecommendAppManager extends EntityService<RecommendApp> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年10月26日
	 * @param id
	 * @param sortNo
	 */
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月26日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<RecommendAppDto> queryDtoForPage(PageInfo<RecommendAppDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月26日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	ScrollPage<RecommendAppDto> queryDtoForPage(ScrollPage<RecommendAppDto> scrollPage, Map<String, Object> conditions);
	
}