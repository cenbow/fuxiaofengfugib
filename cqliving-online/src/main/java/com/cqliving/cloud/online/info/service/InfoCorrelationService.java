package com.cqliving.cloud.online.info.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 相关资讯表 Service
 * Date: 2016-04-15 09:44:43
 * @author Code Generator
 */
public interface InfoCorrelationService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<InfoCorrelation>> queryForPage(PageInfo<InfoCorrelation> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<InfoCorrelation> get(Long id);
	public Response<Void> delete(Long id);
	public Response<Void> save(InfoCorrelation domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param idList
	 * @return
	 */
	Response<Void> clearSortNo(List<Long> idList);
	
	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	Response<Void> modifySortNo(Long id, Integer sortNo);
	
	/**
	 * <p>Description: 移出专题</p>
	 * @author Tangtao on 2016年5月13日
	 * @param infoClasifyIds
	 * @param infoCorrelationIds
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 * @return
	 */
	Response<Void> moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Date updateTime, Long updatorId, String updator);
	
	/**
	 * <p>Description: 移出专题并选择新的栏目</p>
	 * @author Tangtao on 2016年5月13日
	 * @param infoClasifyIds
	 * @param infoCorrelationIds
	 * @param appColumnId
	 * @param updateTime 
	 * @param updatorId 
	 * @param updator 
	 * @return
	 */
	Response<Void> moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Long appColumnId, Date updateTime, Long updatorId, String updator);
	//加入专题
	public Response<Void> joinSpecialInfo(Long[] infoClassifyIds,Long refInfoClassId,Long[] infoThemeIds,Long[] appIds,String sessionUser,Long sessionUserId);
	//设置相关新闻
	public Response<Void> infoCorrelation(Long infoClassifyId,Long refInfoClassifyId,Long appId,String sessionUser,Long sessionUserId);
	//取消相关
	public Response<Void> cancelCorrelation(Long infoClassifyId,Long refInfoClassifyId);
	//加入新闻
	public Response<Void> joinInfo(Long[] infoClassIds,Long refInfoClassifyId,Long appId,Long infoThemeId,String sessionUser,Long sessionUserId);
	//查找关联新闻
	public Response<List<InfoCorrelation>> findByInfoIdAndRefId(Long infoClassifyId,Long refClassifyId);
}
