package com.cqliving.cloud.online.tourism.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.tourism.domain.TourismSpecial;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 旅游专题关系表 Manager
 * Date: 2016-08-23 13:55:36
 * @author Code Generator
 */
public interface TourismSpecialManager extends EntityService<TourismSpecial> {
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
	
	public PageInfo<TourismInfoDto> queryForSpecialSub(PageInfo<TourismInfoDto> pageInfo,
			Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	public void updateSortNo(Long tourismSpecialId,Integer sortNo);
	
	//查询未加入专题的子景点
	public PageInfo<TourismInfoDto> queryForNoJoinSpecial(PageInfo<TourismInfoDto> pageInfo,Map<String,Object> conditions,Map<String,Boolean> orderMap);
	//加入子景点
	public void joinSecial(TourismSpecial tourismSpecial,Long[] refTourismId);

	/**
	 * <p>Description: 子景点列表（无分页）</p>
	 * @author Tangtao on 2016年8月25日
	 * @param appId
	 * @param tourismId
	 * @param lat
	 * @param lng
	 * @return
	 */
	List<TourismInfoDto> queryForSubList(Long appId, Long tourismId, double lat, double lng);
	
}