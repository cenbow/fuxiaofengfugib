package com.cqliving.cloud.online.info.manager;

import java.util.Date;
import java.util.List;

import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.NewsData;
import com.cqliving.framework.common.service.EntityService;

/**
 * 相关资讯表 Manager
 * Date: 2016-04-15 09:44:43
 * @author Code Generator
 */
public interface InfoCorrelationManager extends EntityService<InfoCorrelation> {

	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param idList
	 */
	void clearSortNo(List<Long> idList);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param id
	 * @param sortNo
	 */
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * <p>Description: 移出专题</p>
	 * @author Tangtao on 2016年5月13日
	 * @param infoClasifyIds
	 * @param infoCorrelationIds
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	void moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Date updateTime, Long updatorId, String updator);

	/**
	 * <p>Description: 移出专题并选择新的栏目</p>
	 * @author Tangtao on 2016年5月13日
	 * @param infoClasifyIds
	 * @param infoCorrelationIds
	 * @param appColumnId
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	void moveOut(Long[] infoClasifyIds, Long[] infoCorrelationIds, Long appColumnId, Date updateTime, Long updatorId, String updator);
	//加入专题
	public void joinSpecialInfo(Long[] infoClassifyIds, Long refInfoClassId, Long[] infoThemeIds,Long[] appIds,String sessionUser,Long sessionUserId);
	//设置相关
	public void infoCorrelation(Long infoClassifyId, Long refInfoClassifyId,Long appId,String sessionUser,Long sessionUserId);
	//取消相关
	public void cancelCorrelation(Long infoClassifyId, Long refInfoClassifyId);
	
	public void joinInfo(Long[] infoClassIds, Long refInfoClassifyId, Long appId,Long infoThemeId,String sessionUser,Long sessionUserId);
	
	public List<InfoCorrelation> findByInfoIdAndRefId(Long infoClassifyId, Long refClassifyId);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月1日
	 * @param appId
	 * @param infoClassifyId
	 * @return
	 */
	CommonListResult<NewsData> getCorrelation(Long appId, Long infoClassifyId);
	
}
