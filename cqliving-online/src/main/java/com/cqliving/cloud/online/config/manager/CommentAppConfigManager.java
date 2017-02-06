package com.cqliving.cloud.online.config.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.framework.common.service.EntityService;

/**
 * 评论APP配置表 Manager
 * Date: 2016-06-12 10:01:10
 * @author Code Generator
 */
public interface CommentAppConfigManager extends EntityService<CommentAppConfig> {

	/**
	 * <p>Description: 获取配置值，当App未配置时，获取系统默认配置</p>
	 * @author Tangtao on 2016年6月12日
	 * @param appId
	 * @param name
	 * @param type 
	 * @return
	 */
	Byte getConfigValueByName(Long appId, String name, Byte type);

	/**
	 * <p>Description: 获取评论App配置</p>
	 * @author Tangtao on 2016年6月12日
	 * @param appId
	 * @param type
	 * @return
	 */
	List<CommentAppConfig> getByAppAndType(Long appId, Byte type);

	/**
	 * <p>Description: 保存评论App配置</p>
	 * @author Tangtao on 2016年6月13日
	 * @param appId 
	 * @param userId 
	 * @param nickname 
	 * @param parma
	 * @param type 
	 */
	void save(Long appId, Long userId, String nickname, Map<Long, Byte> parma, Byte type);
	
}
