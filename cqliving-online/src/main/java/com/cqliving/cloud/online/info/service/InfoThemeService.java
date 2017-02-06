package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯专题分类表 Service
 * Date: 2016-04-15 09:45:02
 * @author Code Generator
 */
public interface InfoThemeService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoTheme>> queryForPage(PageInfo<InfoTheme> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoTheme> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoTheme domain);
	/** @author Code Generator *****end*****/
	
	Response<List<InfoTheme>> getByApp(Long appId);
	//根据新闻ID查询专题新闻分类列表
	Response<List<InfoTheme>> findByInfoClassifyId(Long infoClassifyId);
}
