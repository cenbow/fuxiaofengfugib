package com.cqliving.cloud.online.shopping.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 商品分类表 Service
 * Date: 2016-11-17 15:11:46
 * @author Code Generator
 */
public interface ShoppingCategoryService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ShoppingCategory>> queryForPage(PageInfo<ShoppingCategory> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ShoppingCategory> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ShoppingCategory domain);
	public Response<List<ShoppingCategory>> queryList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
	/** @author Code Generator *****end*****/
	
	/**
     * 查询商品分类集合
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月18日上午9:25:00
     */
    public Response<List<ShoppingCategoryDto>> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
    
    /**
     * 查询并返回树的模型数据 
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月18日上午9:25:30
     */
    public Response<List<ShoppingCategoryDto>> queryTreeMode(Map<String, Object> conditions, Map<String, Boolean> orderMap);
    
    /**
     * 批量修改排序值
     * @Description 
     * @param ids
     * @param sortNums
     * @param parentIds
     * @return
     * @author huxiaoping 2016年11月18日下午5:35:26
     */
    public Response<Void> sort(Long[] ids, Integer[] sortNums, Long[] parentIds);
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月21日下午2:31:39
     */
    public Response<Void> saveCategory(ShoppingCategory shoppingCategory);
    
	/**
	 * <p>Description: </p>
	 * @author Tangtao on 2016年11月22日
	 * @param map
	 * @param orderMap
	 * @return
	 */
	Response<List<ShoppingCategory>> queryForList(Map<String, Object> map,Map<String, Boolean> orderMap);
	
}