package com.cqliving.cloud.online.shopping.manager;

import java.util.List;

import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplateDetail;
import com.cqliving.framework.common.service.EntityService;

/**
 * 运费模板明细表 Manager
 * Date: 2016-11-17 15:41:25
 * @author Code Generator
 */
public interface ShoppingFareTemplateDetailManager extends EntityService<ShoppingFareTemplateDetail> {
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
	//和数据库比较，已经删除的要删除
	public void compare(List<ShoppingFareTemplateDetail> detailList,List<ShoppingFareTemplateDetail> sqlList);
	//根据模板查找模板详情
	public List<ShoppingFareTemplateDetail> findFareTemplateId(Long templateId);
}
