package com.cqliving.cloud.online.info.service;

import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoClassifyCommentHis;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯栏目历史推荐表 Service
 * Date: 2016-04-15 09:44:35
 * @author Code Generator
 */
public interface InfoClassifyCommentHisService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoClassifyCommentHis>> queryForPage(PageInfo<InfoClassifyCommentHis> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoClassifyCommentHis> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoClassifyCommentHis domain);
	/** @author Code Generator *****end*****/
	
}
