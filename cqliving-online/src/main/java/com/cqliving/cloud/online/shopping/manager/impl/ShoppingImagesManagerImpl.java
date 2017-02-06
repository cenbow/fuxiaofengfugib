package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.dao.ShoppingImagesDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingImages;
import com.cqliving.cloud.online.shopping.manager.ShoppingImagesManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shoppingImagesManager")
public class ShoppingImagesManagerImpl extends EntityServiceImpl<ShoppingImages, ShoppingImagesDao> implements ShoppingImagesManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingImages.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingImagesManager#compareSql(java.util.List, java.util.List)
	 */
	@Override
	@Transactional(value="transactionManager")
	public void compareSql(List<ShoppingImages> imgs, List<ShoppingImages> sqlImgs) {
		
		if(CollectionUtils.isEmpty(sqlImgs) || CollectionUtils.isEmpty(imgs)){
			return;
		}
		Iterator<ShoppingImages> sql = sqlImgs.iterator();
		while(sql.hasNext()){
			ShoppingImages sqlImg = sql.next();
            boolean delete = true;
            for(ShoppingImages img : imgs){
            	if(null != img.getId() && img.getId().longValue() == sqlImg.getId().longValue()){
            		delete = false;
            		break;
            	}
            }
            if(delete){
            	sqlImg.setStatus(ShoppingImages.STATUS99);
            	this.update(sqlImg);
            }
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingImagesManager#findGoodsId(java.lang.Long)
	 */
	@Override
	public List<ShoppingImages> findGoodsId(Long goodsId) {
		
		return this.getEntityDao().findGoodsId(goodsId);
	}
}
