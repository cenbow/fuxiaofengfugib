package com.cqliving.cloud.online.config.manager;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.ConfigConvenience;
import com.cqliving.cloud.online.config.dto.ConfigConvenienceDto;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.ConvenienceData;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * config_便民表 Manager
 * Date: 2016-07-12 09:33:56
 * @author Code Generator
 */
public interface ConfigConvenienceManager extends EntityService<ConfigConvenience> {
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
	 * Title:修改排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月13日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	public int updateSort(Long id, Integer sortNo);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月13日
	 * @param pageInfo
	 * @param map
	 * @param orderMap
	 * @return
	 */
	public PageInfo<ConfigConvenienceDto> queryPage(PageInfo<ConfigConvenienceDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);

	/**
	 * <p>Description: 获取便民信息</p>
	 * @author Tangtao on 2016年7月13日
	 * @param appId
	 * @return
	 */
	CommonListResult<ConvenienceData> getByApp(Long appId);
	
}