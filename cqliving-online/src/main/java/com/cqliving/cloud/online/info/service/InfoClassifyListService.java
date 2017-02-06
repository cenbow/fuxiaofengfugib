package com.cqliving.cloud.online.info.service;

import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯栏目列表图片表 Service
 * Date: 2016-04-15 09:44:40
 * @author Code Generator
 */
public interface InfoClassifyListService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoClassifyList>> queryForPage(PageInfo<InfoClassifyList> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoClassifyList> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoClassifyList domain);
	/** @author Code Generator *****end*****/
	public Response<InfoClassifyList> findByInfoId(Long infoId);
}
