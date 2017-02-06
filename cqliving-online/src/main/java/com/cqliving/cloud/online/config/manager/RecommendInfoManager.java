package com.cqliving.cloud.online.config.manager;

import java.util.Map;

import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * recommend_info Manager
 * Date: 2016-08-01 14:21:38
 * @author Code Generator
 */
public interface RecommendInfoManager extends EntityService<RecommendInfo> {
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
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月1日
	 * @param pageInfo
	 * @param map
	 * @param orderMap
	 * @param sourceType
	 * @return
	 */
	PageInfo<RecommendInfoDto> queryForPage(PageInfo<RecommendInfoDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap, Byte sourceType);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月1日
	 * @param id
	 * @param sourceType
	 * @return
	 */
	RecommendInfoDto getDetail(Long id, Byte sourceType);
	
	/**
	 * Title:排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月3日
	 * @param id
	 * @param sortNo
	 */
	int updateSort(Long id, Integer sortNo);
}
