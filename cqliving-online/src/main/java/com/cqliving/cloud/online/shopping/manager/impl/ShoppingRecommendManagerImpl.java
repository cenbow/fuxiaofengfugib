package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.dao.ShoppingRecommendDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingRecommendManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shoppingRecommendManager")
public class ShoppingRecommendManagerImpl extends EntityServiceImpl<ShoppingRecommend, ShoppingRecommendDao> implements ShoppingRecommendManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingRecommend.STATUS99, idList);
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
	public List<ShoppingGoodsDto> getCarouselByAppId(Long appId) {
		return getEntityDao().getCarouselByAppId(appId);
	}

	/**
     * 分页查询商城首页信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月22日下午2:45:25
     */
    @Override
    public PageInfo<ShoppingRecommendDto> queryDtoForPage(PageInfo<ShoppingRecommendDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryDtoForPage(pageInfo, map, orderMap);
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

    /**
     * 通过id查询推荐详情
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月23日下午3:43:38
     */
    @Override
    public ShoppingRecommendDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }

	@Override
	public List<ShoppingGoodsDto> getIndex(Map<String, Object> conditionMap, Map<String, Boolean> orderMap) {
		return getEntityDao().getIndex(conditionMap, orderMap);
	}
	
}