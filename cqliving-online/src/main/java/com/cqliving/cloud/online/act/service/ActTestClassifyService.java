package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动答题分类表 Service
 * Date: 2016-06-07 09:22:31
 * @author Code Generator
 */
public interface ActTestClassifyService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActTestClassify>> queryForPage(PageInfo<ActTestClassify> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActTestClassify> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActTestClassify domain);
	public Response<Void> update(ActTestClassify domain);
	/** @author Code Generator *****end*****/
	
	Response<List<ActTestClassify>> getByActTest(Long actInfoListId, Long actTestId);
}
