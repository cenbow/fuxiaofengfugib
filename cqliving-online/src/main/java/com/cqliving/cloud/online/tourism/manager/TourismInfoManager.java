package com.cqliving.cloud.online.tourism.manager;

import java.util.Map;

import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 旅游表 Manager
 * Date: 2016-08-23 13:55:25
 * @author Code Generator
 */
public interface TourismInfoManager extends EntityService<TourismInfo> {
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
	 * <p>Description: 旅游列表</p>
	 * @author Tangtao on 2016年8月24日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<TourismInfoDto> queryDtoForPage(PageInfo<TourismInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年8月24日
	 * @param id
	 * @param sortNo
	 */
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年8月25日
	 * @param scrollPage
	 * @param lat
	 * @param lng
	 * @param appId
	 * @param regionCode
	 * @param type 
	 * @param tourismName
	 * @return
	 */
	ScrollPage<TourismInfoDto> queryForScrollPage(ScrollPage<TourismInfoDto> scrollPage, double lat, double lng, Long appId, String regionCode, Byte type, String tourismName);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月24日
	 * @param tourismInfo
	 * @param images
	 * @param userId
	 * @param userName
	 */
	void saveByAdmin(TourismInfo tourismInfo, String images, Long userId, String userName);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public void updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);	
}