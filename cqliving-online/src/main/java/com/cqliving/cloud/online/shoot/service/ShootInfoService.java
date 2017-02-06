package com.cqliving.cloud.online.shoot.service;

import java.util.Map;

import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ShootInfoData;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 随手拍表 Service
 * Date: 2016-06-07 16:45:09
 * @author Code Generator
 */
public interface ShootInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShootInfo>> queryForPage(PageInfo<ShootInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShootInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShootInfo domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 获取随手拍首页/我的随手拍/随手拍详情</p>
	 * @author Tangtao on 2016年6月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param type
	 * @param shootInfoId 
	 * @param lastId
	 * @return
	 */
	Response<CommonListResult<ShootInfoData>> queryForScrollPage(Long appId, String sessionId, String token, Byte type, Long shootInfoId, Long lastId);
	
	/**
	 * <p>Description: 发布随手拍</p>
	 * @author Tangtao on 2016年6月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param place 
	 * @param lng 
	 * @param lat 
	 * @param content
	 * @param shootType
	 * @param imgs
	 * @param imgDescs
	 * @return
	 */
	Response<Boolean> add(Long appId, String sessionId, String token, String place, String lat, String lng, String content, Byte shootType, String imgs, String imgDescs);
	
	/**
	 * <p>Description: 删除随手拍</p>
	 * @author Tangtao on 2016年6月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	Response<Boolean> remove(Long appId, String sessionId, String token, Long id);
	
	/**
	 * <p>Description: 随手拍列表</p>
	 * @author Tangtao on 2016年6月13日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	Response<PageInfo<ShootInfoDto>> queryDtoForPage(PageInfo<ShootInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);
	
	/**
	 * <p>Description: 修改随手拍状态</p>
	 * @author Tangtao on 2016年6月14日
	 * @param status
	 * @param id
	 * @param userId
	 * @param nickname
	 * @return
	 */
	Response<Void> updateStatus(Byte status, Long id, Long userId, String nickname);
	
}
