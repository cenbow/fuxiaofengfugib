package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 模板图片配置表 Service
 * Date: 2016-05-07 11:41:16
 * @author Code Generator
 */
public interface InfoTempleteImageConfigService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoTempleteImageConfig>> queryForPage(PageInfo<InfoTempleteImageConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoTempleteImageConfig> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(InfoTempleteImageConfig domain);
	/** @author Code Generator *****end*****/
	
	public Response<List<InfoTempleteImageConfig>> getByAppColumnsId(Long appColumnsId,Long appId);
	
	public Response<InfoTempleteImageConfigDto> getById(Long id);
}
