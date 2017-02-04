package com.org.weixin.module.zjchj.manager;

import java.util.Date;
import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.zjchj.domain.BillInfo;
import com.org.weixin.module.zjchj.domain.ZjchjBillInfo;
import com.org.weixin.module.zjchj.dto.ZjchjBillInfoDto;

/**
 * 订单表 Manager
 * Date: 2016-09-26 15:18:38
 * @author Code Generator
 */
public interface ZjchjBillInfoManager extends EntityService<ZjchjBillInfo> {

	/**
	 * <p>Description: 保存订单，生成业务流水号</p>
	 * @author Tangtao on 2016年9月26日
	 * @param openId
	 * @param billInfo
	 * @return
	 */
	String save(String openId, BillInfo billInfo);

	/**
	 * <p>Description: 获取菜品排行统计数据</p>
	 * @author Tangtao on 2016年9月26日
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ZjchjBillInfoDto> getStatistics(Date beginTime, Date endTime);
	
}
