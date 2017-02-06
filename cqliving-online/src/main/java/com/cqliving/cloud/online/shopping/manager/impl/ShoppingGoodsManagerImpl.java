package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.shopping.dao.ShoppingGoodsDao;
import com.cqliving.cloud.online.shopping.dao.ShoppingRecommendDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingImages;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingGoodsManager;
import com.cqliving.cloud.online.shopping.manager.ShoppingImagesManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("shoppingGoodsManager")
public class ShoppingGoodsManagerImpl extends EntityServiceImpl<ShoppingGoods, ShoppingGoodsDao> implements ShoppingGoodsManager {
	
	@Autowired
	private AppInfoDao appInfoDao;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private ShoppingImagesManager shoppingImagesManager;
	@Autowired
	private ShoppingRecommendDao shoppingRecommendDao;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingGoods.STATUS99, idList);
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

	@Override
	public ScrollPage<ShoppingGoodsDto> queryForScrollPage(ScrollPage<ShoppingGoodsDto> scrollPage, Map<String, Object> conditionMap) {
		return getEntityDao().queryForScrollPage(scrollPage, conditionMap);
	}

	@Override
	public List<ShoppingGoodsDto> getMyCart(Long appId, String sessionId, String token) {
		//查询 app 是否存在
		AppInfo appInfo = appInfoDao.get(appId);
		if (appInfo == null) {	//客户端不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getCode(), 
					ErrorCodes.CommonErrorEnum.APP_NOT_EXIST.getDesc() + ": " + appId);
		}
		//获取登录用户
		UserSession userSession = userSessionManager.getByToken(token);
		if (userSession == null) {	//用户不存在
			throw new BusinessException(
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
					ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		
		Map<String, Object> conditionMap = Maps.newHashMap();
		conditionMap.put("EQ_appId", appId);
		conditionMap.put("EQ_userId", userSession.getUserId());
		Map<String, Boolean> orders = Maps.newLinkedHashMap();
		orders.put("cartId", false);
		return getEntityDao().getMyCart(conditionMap, orders);
	}

    @Override
    public PageInfo<ShoppingGoodsDto> queryByPage(PageInfo<ShoppingGoodsDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        
        return this.getEntityDao().queryByPage(pageInfo, map, orderMap);
    }

    @Override
    public ShoppingGoodsDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }

    /**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午5:18:43
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateSortNo(Integer sortNo, Long id) {
        this.getEntityDao().updateSortNo(sortNo, id);
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.shopping.manager.ShoppingGoodsManager#updateShoppingGoods(com.cqliving.cloud.online.shopping.domain.ShoppingGoods)
	 */
	@Override
	@Transactional(value="transactionManager")
	public void updateShoppingGoods(ShoppingGoods shoppingGoods) {
		if(null == shoppingGoods){
			throw new BusinessException(ErrorCodes.FAILURE,"商品信息不能为空");
		}
		List<ShoppingImages> shoppingImages = shoppingGoods.getShoppingImages();
        if(CollectionUtils.isEmpty(shoppingImages)){
        	throw new BusinessException(ErrorCodes.FAILURE,"商品图片不能为空");
		}
        shoppingGoods = this.saveGoods(shoppingGoods);
		shoppingImagesManager.compareSql(shoppingImages, shoppingImagesManager.findGoodsId(shoppingGoods.getId()));
		for(ShoppingImages img : shoppingImages){
			if(null == img){
				continue;
			}
			this.saveShoppingImgs(img, shoppingGoods);
		}
	}
	
	@Transactional(value="transactionManager")
	private void saveShoppingImgs(ShoppingImages img,ShoppingGoods goods){
		
		if(null == img.getId()){
			img.setCreateTime(goods.getUpdateTime());
		}
		img.setShoppingGoodsId(goods.getId());
		img.setStatus(ShoppingImages.STATUS3);
		shoppingImagesManager.save(img);
	}
	
	@Transactional(value="transactionManager")
	private ShoppingGoods saveGoods(ShoppingGoods shoppingGoods){
		if(null == shoppingGoods){
			throw new BusinessException(ErrorCodes.FAILURE,"商品信息不能为空");
		}
		shoppingGoods.setCategoryLevelTwoId(shoppingGoods.getCategoryLevelOneId());
		shoppingGoods.setOriginalPrice(shoppingGoods.getPrice());
		String[] imgSize = BusinessType.getImageConfig(BusinessType.SOURCE_TYPE_17, "thumb");
		String minImg = BusinessType.replaceSize(shoppingGoods.getListImageUrl(), imgSize[0], imgSize[1]);
		shoppingGoods.setMinImageUrl(minImg);
		if(null == shoppingGoods.getId()){
			shoppingGoods.setViewCount(0);
			shoppingGoods.setPraiseCount(0);
			shoppingGoods.setReplyCount(0);
			shoppingGoods.setCollectCount(0);
			
			shoppingGoods.setOnlineTime(shoppingGoods.getUpdateTime());
			shoppingGoods.setStatus(ShoppingGoods.STATUS1);
			shoppingGoods.setSortNo(Integer.MAX_VALUE);
			shoppingGoods.setSalesVolume(0);
			shoppingGoods.setIsRecommendCarousel(ShoppingGoods.ISRECOMMENDCAROUSEL0);
			shoppingGoods.setIsRecommemdIndex(ShoppingGoods.ISRECOMMEMDINDEX0);
			shoppingGoods.setGoodsScore(0);
		}else{
			ShoppingGoods sqlGoods = this.get(shoppingGoods.getId());
			shoppingGoods.setViewCount(sqlGoods.getViewCount());
			shoppingGoods.setPraiseCount(sqlGoods.getPraiseCount());
			shoppingGoods.setReplyCount(sqlGoods.getReplyCount());
			shoppingGoods.setCollectCount(sqlGoods.getCollectCount());
			
			shoppingGoods.setCreateTime(sqlGoods.getCreateTime());
			shoppingGoods.setCreator(sqlGoods.getCreator());
			shoppingGoods.setCreatorId(sqlGoods.getCreatorId());
			shoppingGoods.setOnlineTime(sqlGoods.getOnlineTime());
			shoppingGoods.setStatus(sqlGoods.getStatus());
			shoppingGoods.setSortNo(sqlGoods.getSortNo());
			shoppingGoods.setSalesVolume(sqlGoods.getSalesVolume());
			shoppingGoods.setIsRecommendCarousel(sqlGoods.getIsRecommendCarousel());
			shoppingGoods.setIsRecommemdIndex(sqlGoods.getIsRecommemdIndex());
			shoppingGoods.setGoodsScore(sqlGoods.getGoodsScore());
		}
		return this.getEntityDao().saveAndFlush(shoppingGoods);
	}

	/**
     * 修改推荐到首页
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateIsRecommemdIndex(Byte isRecommemdIndex, ShoppingRecommend recommend) {
        if(null==recommend.getShoppingGoodsId()){
            throw new BusinessException(-1,"商品信息错误，请刷新重试！");
        }
        this.getEntityDao().updateIsRecommemdIndex(isRecommemdIndex, recommend.getShoppingGoodsId());
        shoppingRecommendDao.save(recommend);
    }

    /**
     * 修改推荐到轮播
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateIsRecommendCarousel(Byte isRecommendCarousel, ShoppingRecommend recommend) {
        if(null==recommend.getShoppingGoodsId()){
            throw new BusinessException(-1,"商品信息错误，请刷新重试！");
        }
        this.getEntityDao().updateIsRecommendCarousel(isRecommendCarousel, recommend.getShoppingGoodsId());
        shoppingRecommendDao.save(recommend);
    }
	
}