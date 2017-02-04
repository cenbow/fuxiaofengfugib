package com.org.weixin.module.zjchj.manager;

import java.util.List;

import org.wechat.framework.service.EntityService;

import com.org.weixin.module.zjchj.domain.ZjchjUserGoodsInfo;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;

/**
 * 用户点亮菜品表 Manager
 * Date: 2016-09-26 15:18:51
 * @author Code Generator
 */
public interface ZjchjUserGoodsInfoManager extends EntityService<ZjchjUserGoodsInfo> {

	/**
	 * <p>Description: 点亮菜品</p>
	 * @author Tangtao on 2016年9月26日
	 * @param serialid
	 * @param userId
	 */
	void light(String serialid, Long userId);

	/**
	 * <p>Description: 生成终极中奖用户</p>
	 * @author Tangtao on 2016年10月24日
	 * @return
	 */
	List<Long> getExtremeWin();

	/**
	 * <p>Description: 点亮菜品的用户量</p>
	 * @author Tangtao on 2016年11月2日
	 * @return
	 */
	Long getTotalPeople();

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月3日
	 * @param quantity
	 * @return
	 */
	Long getCountByQuantity(Integer quantity);

	/**
	 * <p>Description: 点亮菜品分布情况</p>
	 * @author Tangtao on 2016年11月14日
	 * @return
	 */
	List<ZjchjAwardDto> getDistribution();
	
}