package com.cqliving.cloud.online.shopping.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;

/**
 * 商品分类持久层接口 
 * Date: 2016-11-17 15:11:46
 * @author Code Generator
 */
public interface ShoppingCategoryDaoCustom {
    /**
     * 查询商品分类集合
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日下午5:00:37
     */
    public List<ShoppingCategoryDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap);
    
    /**
     * 查询并返回树的模型数据
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日下午5:00:37
     */
    public List<ShoppingCategoryDto> queryTreeMode(Map<String, Object> conditions, Map<String, Boolean> orderMap);
}
