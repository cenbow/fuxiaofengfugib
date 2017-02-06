package com.cqliving.cloud.online.wz.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 问题信息收集表 Service
 * Date: 2016-05-10 09:47:41
 * @author Code Generator
 */
public interface WzQuestionCollectInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<WzQuestionCollectInfo>> queryForPage(PageInfo<WzQuestionCollectInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<WzQuestionCollectInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(WzQuestionCollectInfo domain);
	/** @author Code Generator *****end*****/
	
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月13日
	 * @param questionId
	 * @param collectId
	 * @return
	 */
	Response<List<WzQuestionCollectInfo>> getInfoByCollect(Long questionId, Long collectId);
	
}
