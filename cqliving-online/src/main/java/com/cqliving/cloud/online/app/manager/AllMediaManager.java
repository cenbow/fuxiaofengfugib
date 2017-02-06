package com.cqliving.cloud.online.app.manager;

import java.util.Map;

import com.cqliving.cloud.online.app.domain.AllMedia;
import com.cqliving.cloud.online.app.dto.AllMediaDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 全媒体表 Manager
 * Date: 2016-11-02 14:35:32
 * @author Code Generator
 */
public interface AllMediaManager extends EntityService<AllMedia> {
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
	
	public PageInfo<AllMediaDto> queryPage(PageInfo<AllMediaDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	
	public void updateSortNo(Long id, Integer sortNo);
}
