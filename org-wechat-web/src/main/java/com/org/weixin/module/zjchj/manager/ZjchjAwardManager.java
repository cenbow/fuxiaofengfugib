package com.org.weixin.module.zjchj.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.zjchj.domain.ZjchjAward;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;

/**
 * 奖品表 Manager
 * Date: 2016-09-26 15:17:36
 * @author Code Generator
 */
public interface ZjchjAwardManager extends EntityService<ZjchjAward> {
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);

	/**
	 * <p>Description: 抽奖</p>
	 * @author Tangtao on 2016年9月27日
	 * @param userId
	 * @param level
	 * @return
	 */
	ZjchjAward draw(Long userId, Byte level);

	/**
	 * <p>Description: 中奖统计</p>
	 * @author Tangtao on 2016年10月21日
	 * @param beginTime
	 * @param endTime
	 * @param sortMap
	 * @return
	 */
	List<ZjchjAwardDto> queryData(Date beginTime, Date endTime, Map<String, Boolean> sortMap);

}