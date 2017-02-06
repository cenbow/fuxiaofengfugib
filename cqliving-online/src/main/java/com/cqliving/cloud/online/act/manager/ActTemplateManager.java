package com.cqliving.cloud.online.act.manager;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActTemplate;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动模板表 Manager
 * Date: 2016-06-07 09:21:56
 * @author Code Generator
 */
public interface ActTemplateManager extends EntityService<ActTemplate> {
	
	/**
	 * Title:根据app和类型获取模板
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月7日
	 * @param appId
	 * @param type
	 * @return
	 */
	List<ActTemplate> getByApp(Long appId, Byte type);
}
