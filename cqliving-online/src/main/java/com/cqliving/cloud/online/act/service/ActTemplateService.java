package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动模板表 Service
 * Date: 2016-06-07 09:21:56
 * @author Code Generator
 */
public interface ActTemplateService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActTemplate>> queryForPage(PageInfo<ActTemplate> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActTemplate> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(ActTemplate domain);
	/** @author Code Generator *****end*****/
	

	/**
	 * Title:根据app和类型获取模板
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月7日
	 * @param appId
	 * @param type
	 * @return
	 */
	Response<List<ActTemplate>> getByApp(Long appId, Byte type);
}
