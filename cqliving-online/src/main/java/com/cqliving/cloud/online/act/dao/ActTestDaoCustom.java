package com.cqliving.cloud.online.act.dao;

import com.cqliving.cloud.online.act.domain.ActTest;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月23日
 */
public interface ActTestDaoCustom {

	/**
     * Title:根据actInfoList获得活动配置
     * <p>Description:只获取答题类型 and 是已激活状态</p>
     * @author DeweiLi on 2016年6月23日
     * @param actInfoListId
     * @return
     */
	public ActTest getByActListInfo(Long actInfoListId);
	
	/**
	 * Title:根据actTestClass获得
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月23日
	 * @param actTestClassifyId
	 * @return
	 */
	public ActTest getByActTestClassify(Long actTestClassifyId);
}
