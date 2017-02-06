package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯内容表 Service
 * Date: 2016-04-15 09:44:24
 * @author Code Generator
 */
public interface InformationContentService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InformationContent>> queryForPage(PageInfo<InformationContent> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InformationContent> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InformationContent domain);
	/** @author Code Generator *****end*****/
	public Response<List<InformationContent>> findByInfoId(Long infoId);
	//排序
	public Response<Void> infoContentSort(Integer[] sorts,Long[] contentIds);
}
