package com.cqliving.cloud.online.shopping.manager;

import java.util.List;

import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateRegionDetail;
import com.cqliving.framework.common.service.EntityService;

/**
 * 运费模板明细地区表 Manager
 * Date: 2016-11-17 15:41:29
 * @author Code Generator
 */
public interface ShoppingFareTemplateRegionDetailManager extends EntityService<ShoppingFareTemplateRegionDetail> {
	
	//和数据库比较，多余的删除
	public void compareSql(List<ShoppingFareTemplateRegionDetail> list,List<ShoppingFareTemplateRegionDetail> sqlList);
	//根据模板明细查找区域详情
	public List<ShoppingFareTemplateRegionDetail> findByTmpDetailId(Long tmpDetailId);
}
