package com.cqliving.cloud.online.act.dao;

import com.cqliving.cloud.online.act.domain.UserActTestClassify;

public interface UserActTestClassifyDaoCustom {
	
	/**
	 * Title:根据actinfolistId获得用户最后一次答题的信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月29日
	 * @param userId
	 * @param actInfoListId
	 * @return
	 */
	public UserActTestClassify getLast(Long userId, Long actInfoListId);
	
	/**
	 * Title:根据分类id获得用户答题的信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月29日
	 * @param userId
	 * @param classifyId
	 * @return
	 */
	public UserActTestClassify getByClassify(Long userId, Long classifyId);
}
