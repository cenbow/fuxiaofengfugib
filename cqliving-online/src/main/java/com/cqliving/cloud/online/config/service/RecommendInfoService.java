package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * recommend_info Service
 * Date: 2016-08-01 14:21:38
 * @author Code Generator
 */
public interface RecommendInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<RecommendInfoDto>> queryForPage(PageInfo<RecommendInfoDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap, Byte sourceType);
	public Response<RecommendInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(RecommendInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月1日
	 * @param id
	 * @param sourceType
	 * @return
	 */
	public Response<RecommendInfoDto> getDetail(Long id, Byte sourceType);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月3日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	public Response<Void> updateSort(Long id, Integer sortNo);
}
