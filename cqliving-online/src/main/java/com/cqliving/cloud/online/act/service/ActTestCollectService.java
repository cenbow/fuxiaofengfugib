package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动答题表 Service
 * Date: 2016-06-07 09:22:46
 * @author Code Generator
 */
public interface ActTestCollectService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActTestCollect>> queryForPage(PageInfo<ActTestCollect> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActTestCollect> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActTestCollect domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:获得答题所选的收集信息集合
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月14日
	 * @param actTestId
	 * @return
	 */
	Response<List<ActTestCollect>> getByActTestId(Long actTestId);
	
}
