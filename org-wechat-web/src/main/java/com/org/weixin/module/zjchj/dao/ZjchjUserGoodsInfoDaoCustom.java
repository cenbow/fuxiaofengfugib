package com.org.weixin.module.zjchj.dao;


import java.util.List;

import com.org.weixin.module.zjchj.domain.ZjchjUserGoodsInfo;
import com.org.weixin.module.zjchj.dto.ZjchjAwardDto;

public interface ZjchjUserGoodsInfoDaoCustom {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年10月24日
	 * @return
	 */
	List<ZjchjUserGoodsInfo> getStatistics();
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月3日
	 * @param quantity
	 * @return
	 */
	Long getCountByQuantity(Integer quantity);
	
	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月14日
	 * @return
	 */
	List<ZjchjAwardDto> getDistribution();

}