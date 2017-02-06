package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 评论APP配置表 Service
 * Date: 2016-06-12 10:01:10
 * @author Code Generator
 */
public interface CommentAppConfigService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<CommentAppConfig>> queryForPage(PageInfo<CommentAppConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<CommentAppConfig> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(CommentAppConfig domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取评论App配置</p>
	 * @author Tangtao on 2016年6月12日
	 * @param appId
	 * @param type
	 * @return
	 */
	Response<List<CommentAppConfig>> getByAppAndType(Long appId, Byte type);
	
	/**
	 * <p>Description: 保存评论App配置</p>
	 * @author Tangtao on 2016年6月13日
	 * @param appId 
	 * @param userId 
	 * @param nickname 
	 * @param parma 
	 * @param type 
	 * @return
	 */
	Response<Void> save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, Byte type);
	
	/**
	 * <p>Description: 获取具体配置的值</p>
	 * @author Tangtao on 2016年7月22日
	 * @param appId
	 * @param name
	 * @param type
	 * @return
	 */
	Response<Byte> getConfigValueByName(Long appId, String name, Byte type);
	
}
