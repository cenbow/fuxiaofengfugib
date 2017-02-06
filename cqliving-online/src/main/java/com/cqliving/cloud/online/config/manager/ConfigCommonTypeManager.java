package com.cqliving.cloud.online.config.manager;

import com.cqliving.framework.common.service.EntityService;

import java.util.List;

import com.cqliving.cloud.online.config.domain.ConfigCommonType;

/**
 * config_common_type Manager
 * Date: 2016-07-25 13:54:08
 * @author Code Generator
 */
public interface ConfigCommonTypeManager extends EntityService<ConfigCommonType> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月26日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	public int updateSort(Long id, Integer sortNo);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月26日
	 * @param sourceType
	 * @param appId
	 * @return
	 */
	public List<ConfigCommonType> getBySourceType(Long appId, Byte sourceType);

	/**
	 * <p>Description: 获取话题分类</p>
	 * @author Tangtao on 2016年7月27日
	 * @param ids
	 * @return
	 */
	List<ConfigCommonType> getByIds(List<Long> ids);
	
}
