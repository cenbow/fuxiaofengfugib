package com.cqliving.cloud.online.config.manager;

import java.util.List;

import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.framework.common.service.EntityService;

/**
 * 评论配置表 Manager
 * Date: 2016-06-12 10:01:52
 * @author Code Generator
 */
public interface CommentConfigManager extends EntityService<CommentConfig> {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年7月6日
	 * @param type
	 * @return
	 */
	List<CommentConfig> getByType(Byte type);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月26日
	 * @param type
	 * @param name
	 * @return
	 */
	CommentConfig getByTypeAndName(Byte type, String name);
}