package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 资讯来源表 Service
 * Date: 2016-04-15 09:44:51
 * @author Code Generator
 */
public interface InfoSourceService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoSource>> queryForPage(PageInfo<InfoSource> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoSource> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoSource domain);
	/** @author Code Generator *****end*****/
	public Response<List<InfoSource>> findByConditions(Map<String,Object> conditions);
	//修改状态
	public Response<Void> updateStatus(Byte status,Long... id);
	//修改排序号
	public Response<Void> updateSortNo(Long id,Integer sortNo);
	
	public Response<PageInfo<InfoSourceDto>> queryForPage(PageInfo<InfoSourceDto> pageInfo, Map<String, Object> map);
}
