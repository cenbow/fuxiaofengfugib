package com.cqliving.cloud.online.shopping.manager;

import java.util.List;

import com.cqliving.cloud.online.shopping.domain.ShoppingImages;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商品图片表 Manager
 * Date: 2016-11-17 15:41:36
 * @author Code Generator
 */
public interface ShoppingImagesManager extends EntityService<ShoppingImages> {
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
	
	public void compareSql(List<ShoppingImages> imgs,List<ShoppingImages> sqlImgs);
	
	public List<ShoppingImages> findGoodsId(Long goodsId);
}
