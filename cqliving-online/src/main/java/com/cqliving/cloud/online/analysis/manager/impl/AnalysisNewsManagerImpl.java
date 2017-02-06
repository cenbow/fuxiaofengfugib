package com.cqliving.cloud.online.analysis.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.EchartsXaxisUtil;
import com.cqliving.cloud.online.analysis.dao.AnalysisNewsDao;
import com.cqliving.cloud.online.analysis.domain.AnalysisNews;
import com.cqliving.cloud.online.analysis.manager.AnalysisNewsManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.echarts.EchartsUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("analysisNewsManager")
public class AnalysisNewsManagerImpl extends EntityServiceImpl<AnalysisNews, AnalysisNewsDao> implements AnalysisNewsManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.manager.AnalysisNewsManager#findByConditions(java.util.Map, java.util.Map)
	 */
	@Override
	public Map<String,List<Map<String,String>>> findByConditions(Map<String, Object> conditions, Map<String, Boolean> sortMap) {
		
		List<AnalysisNews> data = this.getEntityDao().findByConditions(conditions, sortMap);
//		if(CollectionUtils.isEmpty(data)){
//			return null;
//		}
		Map<String,List<Map<String,String>>> echartsMap = Maps.newLinkedHashMap();
		List<Map<String,String>> viewNumlist = Lists.newArrayList();
		List<Map<String,String>> commentNumlist = Lists.newArrayList();
		List<Map<String,String>> praiseNumlist = Lists.newArrayList();
		List<Map<String,String>> favoriteNumlist = Lists.newArrayList();
		List<Map<String,String>> shareNumlist = Lists.newArrayList();
		
		List<Map<Integer,String>> xData = EchartsXaxisUtil.getHoursXaxis();
		
		for(Map<Integer,String> map : xData){//X轴坐标数据必须对应上当前x轴实际数据
			
			Map<String,String> viewNumlineData = Maps.newLinkedHashMap();
			Map<String,String> commentNumlineData = Maps.newLinkedHashMap();
			Map<String,String> praiselineData = Maps.newLinkedHashMap();
			Map<String,String> favoritelineData = Maps.newLinkedHashMap();
			Map<String,String> sharelineData = Maps.newLinkedHashMap();
			viewNumlineData.put(EchartsUtil.SERIES_NAME,"浏览量");
			commentNumlineData.put(EchartsUtil.SERIES_NAME,"评论数");
			/*viewNumlineData.put(EchartsUtil.SERIES_COLOR, AnalysisNews.LINE_COLOR.get(AnalysisNews.VIEWNUM_COLOR));
			commentNumlineData.put(EchartsUtil.SERIES_COLOR, AnalysisNews.LINE_COLOR.get(AnalysisNews.COMMENTNUM_COLOR));
			praiselineData.put(EchartsUtil.SERIES_COLOR, AnalysisNews.LINE_COLOR.get(AnalysisNews.PRAISENUM_COLOR));
			favoritelineData.put(EchartsUtil.SERIES_COLOR, AnalysisNews.LINE_COLOR.get(AnalysisNews.FAVORITENUM_COLOR));*/
			/*sharelineData.put(EchartsUtil.SERIES_COLOR, AnalysisNews.LINE_COLOR.get(AnalysisNews.SHARENUM_COLOR));*/
			praiselineData.put(EchartsUtil.SERIES_NAME,"点赞数");
			favoritelineData.put(EchartsUtil.SERIES_NAME,"收藏数");
			sharelineData.put(EchartsUtil.SERIES_NAME,"分享数");
			viewNumlineData.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			commentNumlineData.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			praiselineData.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			favoritelineData.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			sharelineData.put(EchartsUtil.SERIES_VALUE,EchartsUtil.DEFAULT_NUM);
			if(CollectionUtils.isNotEmpty(data)){
				for(AnalysisNews analysisNews : data){
					Integer hour = analysisNews.getHour();
					if(map.containsKey(hour)){
						viewNumlineData.put(EchartsUtil.SERIES_VALUE, String.valueOf(analysisNews.getViewNum()));
						commentNumlineData.put(EchartsUtil.SERIES_VALUE, String.valueOf(analysisNews.getCommentNum()));
						praiselineData.put(EchartsUtil.SERIES_VALUE, String.valueOf(analysisNews.getPraiseNum()));
						favoritelineData.put(EchartsUtil.SERIES_VALUE, String.valueOf(analysisNews.getFavoriteNum()));
						sharelineData.put(EchartsUtil.SERIES_VALUE, String.valueOf(analysisNews.getShareNum()));
					}
				}
			}
			viewNumlist.add(viewNumlineData);
			commentNumlist.add(commentNumlineData);
			praiseNumlist.add(praiselineData);
			favoriteNumlist.add(favoritelineData);
			shareNumlist.add(sharelineData);
		}
		echartsMap.put("浏览量", viewNumlist);
		echartsMap.put("评论数", commentNumlist);
		echartsMap.put("点赞数", praiseNumlist);
		echartsMap.put("收藏数", favoriteNumlist);
		echartsMap.put("分享数", shareNumlist);
		return echartsMap;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.analysis.manager.AnalysisNewsManager#callProcedure()
	 */
	@Override
	@Transactional
	public void callProcedure() {
		
		this.getEntityDao().callProcedure();
	}
}
