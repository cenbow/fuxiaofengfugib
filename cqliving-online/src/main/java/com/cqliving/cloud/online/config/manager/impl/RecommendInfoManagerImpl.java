package com.cqliving.cloud.online.config.manager.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.config.dao.RecommendInfoDao;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.dto.RecommendInfoDto;
import com.cqliving.cloud.online.config.manager.RecommendInfoManager;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.topic.dao.TopicInfoDao;
import com.cqliving.cloud.online.topic.domain.TopicInfo;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;

@Service("recommendInfoManager")
public class RecommendInfoManagerImpl extends EntityServiceImpl<RecommendInfo, RecommendInfoDao> implements RecommendInfoManager {
	
	@Autowired
	private TopicInfoDao topicInfoDao;
	@Autowired
	private InfoClassifyDao infoClassifyDao;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		List<RecommendInfo> list = this.getEntityDao().getListByIds(idList);
		
		List<Long> tmpIds1 = Lists.newArrayList();
		List<Long> tmpIds7 = Lists.newArrayList();
		for(RecommendInfo data : list){
			if(RecommendInfo.SOURCETYPE1.equals(data.getSourceType())){//新闻
				tmpIds1.add(data.getSourceId());
			}else if(RecommendInfo.SOURCETYPE7.equals(data.getSourceType())){//话题
				tmpIds7.add(data.getSourceId());
			}
		}
		//新闻
		if(tmpIds1.size() > 0){
			infoClassifyDao.updateRecommendStatus(InfoClassify.ISRECOMMEND0, tmpIds1);
		}
		//话题
		if(tmpIds7.size() > 0){
			topicInfoDao.updateTopStatus(TopicInfo.ISTOP0, tmpIds7);
		}
		return this.getEntityDao().updateStatus(RecommendInfo.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		if(!RecommendInfo.allStatuss.containsKey(status)){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
		}
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	@Override
	public PageInfo<RecommendInfoDto> queryForPage(PageInfo<RecommendInfoDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap, Byte sourceType) {
		return this.getEntityDao().queryForPage(pageInfo, map, orderMap, sourceType);
	}

	@Override
	public RecommendInfoDto getDetail(Long id, Byte sourceType) {
		return this.getEntityDao().getDetail(id, sourceType);
	}

	@Override
	@Transactional(value="transactionManager")
	public int updateSort(Long id, Integer sortNo) {
		return this.getEntityDao().updateSort(id, sortNo);
	}
}
