package com.cqliving.cloud.online.act.manager;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动答题表 Manager
 * Date: 2016-06-07 09:22:46
 * @author Code Generator
 */
public interface ActTestCollectManager extends EntityService<ActTestCollect> {

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月14日
	 * @param actTestId
	 * @return
	 */
	List<ActTestCollect> getByActTestId(Long actTestId);
}
