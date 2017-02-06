package com.cqliving.cloud.online.tourism.manager;

import java.util.List;

import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 旅游图片表 Manager
 * Date: 2016-08-23 13:55:07
 * @author Code Generator
 */
public interface TourismImageManager extends EntityService<TourismImage> {
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
	
	public List<TourismImage> findByTourismId(Long tourismId);
}
