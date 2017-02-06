package com.cqliving.cloud.online.act.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.act.domain.UserActTestClassify;

/**
 * 用户答题分类表，一个用户对应一个分类测试题只有一条记录。 Manager
 * Date: 2016-06-22 18:02:02
 * @author Code Generator
 */
public interface UserActTestClassifyManager extends EntityService<UserActTestClassify> {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月29日
	 * @param actTestId
	 * @param actTestClassifyId
	 * @param userId
	 * @return
	 */
	UserActTestClassify getByWhere(Long actTestId, Long actTestClassifyId, Long userId);
}
