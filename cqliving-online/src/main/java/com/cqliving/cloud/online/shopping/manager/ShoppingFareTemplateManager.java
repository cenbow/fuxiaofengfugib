package com.cqliving.cloud.online.shopping.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;
import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 运费模板表 Manager
 * Date: 2016-11-17 15:41:20
 * @author Code Generator
 */
public interface ShoppingFareTemplateManager extends EntityService<ShoppingFareTemplate> {
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
	
	public void saveOrUpdate(ShoppingFareTemplate fareTemplate);
	
	public List<ShoppingFareTemplateDto> findConditions(Map<String, Object> conditions);
}
