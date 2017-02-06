package com.cqliving.cloud.online.shopping.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.shopping.dao.ShoppingCategoryDao;
import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.cloud.online.shopping.manager.ShoppingCategoryManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("shoppingCategoryManager")
public class ShoppingCategoryManagerImpl extends EntityServiceImpl<ShoppingCategory, ShoppingCategoryDao> implements ShoppingCategoryManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ShoppingCategory.STATUS99, idList);
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
    public List<ShoppingCategoryDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        return this.getEntityDao().getList(conditions, orderMap);
    }

    @Override
    public List<ShoppingCategoryDto> queryTreeMode(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryTreeMode(conditions, orderMap);
    }

    @Override
    @Transactional(value="transactionManager")
    public void sort(Long[] ids, Integer[] sortNums, Long[] parentIds) {
        for(int j=0; j<ids.length; j++){
            super.getEntityDao().sort(ids[j], sortNums[j], parentIds[j]);
        }
    }

    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月21日下午2:10:29
     */
    @Override
    @Transactional(value="transactionManager")
    public void saveCategory(ShoppingCategory category) {
        //设置排序号
        Integer maxSortNo  = this.getEntityDao().getMaxSortNo(category.getAppId());
        category.setSortNo(null==maxSortNo ? 1 : maxSortNo + 1);
        save(category);
    }
}
