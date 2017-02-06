package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigConvenience;
import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ConvenienceData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * config_便民表 Service
 * Date: 2016-07-12 09:33:56
 * @author Code Generator
 */
public interface ConfigConvenienceService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigConvenienceDto>> queryForPage(PageInfo<ConfigConvenienceDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigConvenience> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ConfigConvenience domain);
	/** @author Code Generator *****end*****/
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月13日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	public Response<Void> updateSort(Long id, Integer sortNo);
	
	/**
	 * <p>Description: 获取便民信息</p>
	 * @author Tangtao on 2016年7月13日
	 * @param appId
	 * @return
	 */
	Response<CommonListResult<ConvenienceData>> getByApp(Long appId);
	
}