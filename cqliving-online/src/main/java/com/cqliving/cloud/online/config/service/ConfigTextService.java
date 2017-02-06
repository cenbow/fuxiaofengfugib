package com.cqliving.cloud.online.config.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.tool.common.Response;

/**
 * 联系我们、区情介绍、反馈回复 Service
 * Date: 2016-07-13 17:16:59
 * @author Code Generator
 */
public interface ConfigTextService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConfigText>> queryForPage(PageInfo<ConfigText> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConfigText> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ConfigText domain);
	/** @author Code Generator *****end*****/
	/**
	 * 通过appId和type查询
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年7月14日下午5:08:54
	 */
	public Response<ConfigText> getByAppId(Long appId,Byte type);
	
}
