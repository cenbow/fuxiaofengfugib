package com.cqliving.cloud.online.info.manager;

import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯栏目推荐表 Manager
 * Date: 2016-04-15 09:44:31
 * @author Code Generator
 */
public interface InfoClassifyCommentManager extends EntityService<InfoClassifyComment> {

	/**
	 * <p>Description: 忽略推荐</p>
	 * @author Tangtao on 2016年5月11日
	 * @param id
	 */
	void ignore(Long id);
	
	/**
	 * <p>Description: 发布到App</p>
	 * @author Tangtao on 2016年5月12日
	 * @param appId
	 * @param infoClassifyId
	 * @param id
	 * @param appColumnId
	 * @param userName
	 * @param userId
	 */
	void publishToColumn(Long appId, Long infoClassifyId, Long id, Long appColumnId, String userName, Long userId);

}
