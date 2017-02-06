package com.cqliving.cloud.online.shopping.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 商品分类表 Manager
 * Date: 2016-11-17 15:11:46
 * @author Code Generator
 */
public interface ShoppingCategoryManager extends EntityService<ShoppingCategory> {
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
	 * 查询商品分类集合
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月18日上午9:25:00
	 */
	public List<ShoppingCategoryDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	/**
	 * 查询并返回树的模型数据 
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月18日上午9:25:30
	 */
	public List<ShoppingCategoryDto> queryTreeMode(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	
	/**
     * 批量修改排序值
     * @Description 
     * @param ids
     * @param sortNums
     * @param parentIds
     * @return
     * @author huxiaoping 2016年11月18日下午5:35:26
     */
    public void sort(Long[] ids, Integer[] sortNums, Long[] parentIds);
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月21日下午2:10:29
     */
    public void saveCategory(ShoppingCategory category);
}
