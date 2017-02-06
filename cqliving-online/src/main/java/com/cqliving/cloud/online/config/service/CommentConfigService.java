package com.cqliving.cloud.online.config.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 评论配置表 Service
 * Date: 2016-06-12 10:01:52
 * @author Code Generator
 */
public interface CommentConfigService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<CommentConfig>> queryForPage(PageInfo<CommentConfig> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<CommentConfig> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(CommentConfig domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月6日
	 * @param type
	 * @return
	 */
	Response<List<CommentConfig>> getByType(Byte type);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月26日
	 * @param type
	 * @param name
	 * @return
	 */
	Response<CommentConfig> getByTypeAndName(Byte type, String name);
}
