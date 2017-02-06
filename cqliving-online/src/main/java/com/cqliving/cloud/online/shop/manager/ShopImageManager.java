package com.cqliving.cloud.online.shop.manager;

import java.util.List;

import com.cqliving.cloud.online.shop.domain.ShopImage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商铺图片表 Manager
 * Date: 2016-05-16 20:41:26
 * @author Code Generator
 */
public interface ShopImageManager extends EntityService<ShopImage> {
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
	 * <p>Description: 获取商铺图片</p>
	 * @author Tangtao on 2016年5月21日
	 * @param id
	 * @return
	 */
	List<ShopImage> getByShop(Long id);
	
}
