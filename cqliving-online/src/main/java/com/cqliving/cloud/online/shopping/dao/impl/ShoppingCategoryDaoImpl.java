package com.cqliving.cloud.online.shopping.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.shopping.dao.ShoppingCategoryDaoCustom;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

public class ShoppingCategoryDaoImpl implements ShoppingCategoryDaoCustom{
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
    /**
     * 查询商品分类集合
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日下午5:00:37
     */
    @Override
    public List<ShoppingCategoryDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM shopping_category ");
        return mysqlPagedJdbcTemplateV2.queryForList(ShoppingCategoryDto.class, sql.toString(), conditions , orderMap);
    }

    /**
     * 查询并返回树的模型数据
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日下午5:00:37
     */
    @Override
    public List<ShoppingCategoryDto> queryTreeMode(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        List<ShoppingCategoryDto> list = getList(conditions, orderMap);
        if(null!=list&&list.size()>=2){
            return wrapper(list);
        }
        return list;
    }
    
    /**
     * 组装tree模型
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日下午5:11:37
     */
    private List<ShoppingCategoryDto> wrapper(List<ShoppingCategoryDto> list){
        
        //去重复
        //uniqueById(list);
        
        //组装
        Iterator<ShoppingCategoryDto> it = list.listIterator();

        //各自寻找自己的子节点
        while (it.hasNext()) {
            ShoppingCategoryDto categoryDto = it.next();
            TreeSet<ShoppingCategoryDto> subCategorySet = new TreeSet<ShoppingCategoryDto>();
            for (ShoppingCategoryDto dto : list) {
                if (null != dto.getParentId() && dto.getParentId().longValue() == categoryDto.getId().longValue()) {
                    subCategorySet.add(dto);
                }
            }
            if (!CollectionUtils.isEmpty(subCategorySet)){
                categoryDto.setSubs(subCategorySet);
            }
        }
        
        // 删除子，只保留父，因为子已放在父的子集里面了
        it = list.listIterator();
        while (it.hasNext()) {
            ShoppingCategoryDto categoryDto = it.next();
            if ((null != categoryDto && categoryDto.getParentId() != null && categoryDto.getParentId() != 0l)) {
                it.remove();
            }
        }
        
        return list;
    }
    
    /**
     * 去重复数据
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日下午5:13:50
     */
    private List<ShoppingCategoryDto> uniqueById(List<ShoppingCategoryDto> list) {
        if (CollectionUtils.isEmpty(list))
            return null;
        // 根据ID排重
        for (int i = 0; i < list.size(); i++) {
            // list的大小每次都重新取
            ShoppingCategoryDto dto1 = list.get(i);
            for (int j = list.size() - 1; j >= i + 1; j--) {// 倒着取，取到i的前一个截止
                ShoppingCategoryDto dto2 = list.get(j);
                if (dto1.getId().longValue() == dto2.getId().longValue()) {// 重复
                    list.remove(dto2);
                }
            }
        }
        return list;
    }
}
