package com.cqliving.cloud.online.shoot.manager;

import java.util.Map;

import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ShootInfoData;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 随手拍表 Manager
 * Date: 2016-06-07 16:45:09
 * @author Code Generator
 */
public interface ShootInfoManager extends EntityService<ShootInfo> {
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
	CommonListResult<ShootInfoData> queryForScrollPage(Long appId, String sessionId, String token, Byte type, Long shootInfoId, Long lastId);

	/**
	 * <p>Description: 发布随手拍</p>
	 * @author Tangtao on 2016年6月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param place
	 * @param lat
	 * @param lng
	 * @param content
	 * @param shootType
	 * @param imgUrls
	 * @param imgDescs
	 * @return 返回保存成功后随手拍的状态
	 */
	Byte add(Long appId, String sessionId, String token, String place, String lat, String lng, String content, Byte shootType, String imgUrls, String imgDescs);

	/**
	 * <p>Description: 删除随手拍</p>
	 * @author Tangtao on 2016年6月8日
	 * @param appId
	 * @param sessionId
	 * @param token
	 * @param id
	 * @return
	 */
	boolean remove(Long appId, String sessionId, String token, Long id);

	/**
	 * <p>Description: 随手拍列表</p>
	 * @author Tangtao on 2016年6月13日
	 * @param pageInfo
	 * @param searchMap
	 * @param sortMap
	 * @return
	 */
	PageInfo<ShootInfoDto> queryDtoForPage(PageInfo<ShootInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap);

	/**
	 * <p>Description: 修改随手拍状态</p>
	 * @author Tangtao on 2016年6月14日
	 * @param status
	 * @param id
	 * @param userId
	 * @param nickname
	 */
	void updateStatus(Byte status, Long id, Long userId, String nickname);
	
}
