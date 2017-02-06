package com.cqliving.cloud.online.analysis.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.EchartsXaxisUtil;
import com.cqliving.cloud.online.analysis.dao.AnalysisAppColumnsDao;
import com.cqliving.cloud.online.analysis.domain.AnalysisAppColumns;
import com.cqliving.cloud.online.analysis.dto.AnalysisAppColumnsDto;
import com.cqliving.cloud.online.analysis.manager.AnalysisAppColumnsManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.echarts.EchartsUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("analysisAppColumnsManager")
public class AnalysisAppColumnsManagerImpl extends EntityServiceImpl<AnalysisAppColumns, AnalysisAppColumnsDao> implements AnalysisAppColumnsManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.manager.AnalysisAppColumnsManager#callProcedure()
	 */
	@Override
	@Transactional
	public void callProcedure() {
		this.getEntityDao().callProcedure();
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.manager.AnalysisAppColumnsManager#findByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	public Map<String, List<Map<String, String>>> findByConditions(Map<String, Object> conditions,
			Map<String, Boolean> sortMap) {
		
		Integer date = (Integer) conditions.get("diffDay");
		conditions.remove("diffDay");
		List<AnalysisAppColumns> data = this.getEntityDao().findByConditions(conditions, sortMap);
		if(CollectionUtils.isEmpty(data))return null;
		
		Map<String, List<Map<String, String>>> seriesMap = Maps.newLinkedHashMap();
		
		List<Map<String, String>> viewList = Lists.newArrayList();
		List<Map<String, String>> commentList = Lists.newArrayList();
		List<Map<String, String>> favList = Lists.newArrayList();
		List<Map<String, String>> shareList = Lists.newArrayList();
		
		List<Map<Integer,String>> xAxis = EchartsXaxisUtil.getDayXaxis(Dates.now(),date);//x轴数据
		
		for(Map<Integer,String> map : xAxis){//只有在x轴中对应的日期有数据才不为0
			Map<String, String> viewMap = Maps.newHashMap();
			Map<String, String> commentMap = Maps.newHashMap();
			Map<String, String> favMap = Maps.newHashMap();
			Map<String, String> shareMap = Maps.newHashMap();
			viewMap.put(EchartsUtil.SERIES_NAME,"浏览量");
			commentMap.put(EchartsUtil.SERIES_NAME,"评论量");
			favMap.put(EchartsUtil.SERIES_NAME,"收藏量");
			shareMap.put(EchartsUtil.SERIES_NAME,"分享量");
			viewMap.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			commentMap.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			favMap.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			shareMap.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			//x轴对应数据
			for(AnalysisAppColumns sqlData : data){
				Date dates = sqlData.getStatisticsDate();
				String dateStr = DateUtil.format(dates, DateUtil.FORMAT_YYYY_MM_DD);
				if(map.containsValue(dateStr)){
					viewMap.put(EchartsUtil.SERIES_VALUE,sqlData.getViewNum().toString());
					commentMap.put(EchartsUtil.SERIES_VALUE,sqlData.getCommentNum().toString());
					favMap.put(EchartsUtil.SERIES_VALUE,sqlData.getFavoriteNum().toString());
					shareMap.put(EchartsUtil.SERIES_VALUE,sqlData.getShareNum().toString());
				}
			}
			viewList.add(viewMap);
			commentList.add(commentMap);
			favList.add(favMap);
			shareList.add(shareMap);
		}
		seriesMap.put("浏览量", viewList);
		seriesMap.put("评论量", commentList);
		seriesMap.put("收藏量", favList);
		seriesMap.put("分享量", shareList);
		return seriesMap;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.manager.AnalysisAppColumnsManager#findPageInfo(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map)
	 */
	@Override
	public PageInfo<AnalysisAppColumnsDto> findPageInfo(PageInfo<AnalysisAppColumnsDto> pageInfo,
			Map<String, Object> conditions) {
		
		return this.getEntityDao().findPageInfo(pageInfo, conditions);
	}
}
