package com.cqliving.cloud.online.info.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.info.domain.InfoSliderConfig;
import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;
import com.cqliving.tool.common.Response;

/**
 * 资讯轮播图配置 Service
 * Date: 2016-08-30 10:19:57
 * @author Code Generator
 */
public interface InfoSliderConfigService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoSliderConfig>> queryForPage(PageInfo<InfoSliderConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoSliderConfig> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(InfoSliderConfig domain);
	/** @author Code Generator *****end*****/
	/**
	 * 通过appId查询轮播图配置列表信息
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年8月30日下午2:17:14
	 */
	public Response<List<InfoSliderConfigDto>> getListByAppId(Long appId);
	
	/**
	 * 保存轮播图配置
	 * @Description 
	 * @Company 
	 * @parameter 
	 * <p>appId：配置所属app</p>
	 * <p>columnsId：配置所属栏目</p>
	 * <p>value：配置值</p>
	 * <p>userId：操作人Id</p>
	 * <p>userNmae：操作人名称</p>
	 * @return
	 * @author huxiaoping 2016年8月30日下午4:00:54
	 */
	public Response<Void> save(Long appId,Long[] id,Long columnsId[],Integer[] value,Long userId,String userNmae);
	
}
