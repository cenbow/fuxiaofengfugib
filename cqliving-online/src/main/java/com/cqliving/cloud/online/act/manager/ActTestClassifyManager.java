package com.cqliving.cloud.online.act.manager;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动答题分类表 Manager
 * Date: 2016-06-07 09:22:31
 * @author Code Generator
 */
public interface ActTestClassifyManager extends EntityService<ActTestClassify> {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月12日
	 * @param actInfoListId
	 * @param actTestId
	 * @return
	 */
	List<ActTestClassify> getByActTest(Long actInfoListId, Long actTestId);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月13日
	 * @param ids
	 * @return
	 */
	List<ActTestClassify> getByIds(List<Long> ids);
}
