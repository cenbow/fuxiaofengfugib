package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.dao.ActInfoDao;
import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.act.manager.ActInfoManager;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("actInfoManager")
public class ActInfoManagerImpl extends EntityServiceImpl<ActInfo, ActInfoDao> implements ActInfoManager {
	
	@Autowired
	ActInfoListDao actInfoListDao;
	@Autowired
	ActInfoDao actInfoDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public void deleteLogic(String updator,Long updateUserId, Long... ids){
		List<Long> idList = Arrays.asList(ids);
		this.getEntityDao().updateStatus(ActInfo.STATUS99, updator, updateUserId, new Date(), idList);
		actInfoListDao.updateStatusByActInfoId(ActInfoList.STATUS99, updator, updateUserId, new Date(), idList);
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
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public void updateStatus(Byte status,String updator,Long updateUserId,Long... ids){
	    List<Long> idList = Arrays.asList(ids);
	    this.getEntityDao().updateStatus(status, updator, updateUserId, new Date(), idList);
	}
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月7日上午10:37:12
     */
    @Override
    public PageInfo<ActInfoDto> queryPage(PageInfo<ActInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryPage(pageInfo, map, orderMap);
    }
    
    /* (non-Javadoc)
     * @see com.cqliving.cloud.online.act.manager.ActInfoManager#findById(java.lang.Long)
     */
    @Override
    public ActInfoDto findById(Long id) {
        
        return this.getEntityDao().findById(id);
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.ActInfoManager#saveOrUpdate(com.cqliving.cloud.online.act.dto.ActInfoDto)
	 */
	@Override
	@Transactional(value="transactionManager")
	public ActInfoDto saveOrUpdate(ActInfoDto actInfoDto) {
		
		//公告//外链//删除
		Long actInfoId = actInfoDto.getId();
		ActInfo actInfo = null;
		if(null != actInfoId && 0 != actInfoId ){
			actInfo = this.get(actInfoId);
			if(null == actInfo)throw new BusinessException(ErrorCodes.FAILURE,"此活动不存在");
			
			actInfo.setActImageUrl(actInfoDto.getActImageUrl());
			actInfo.setAppId(actInfoDto.getAppId());
			actInfo.setContent(actInfoDto.getContent());
			actInfo.setDigest(actInfoDto.getDigest());
			actInfo.setEndTime(actInfoDto.getEndTime());
			actInfo.setListImageUrl(actInfoDto.getListImageUrl());
			actInfo.setStartTime(actInfoDto.getStartTime());
			actInfo.setTitle(actInfoDto.getTitle());
			actInfo.setUpdateTime(actInfoDto.getUpdateTime());
			actInfo.setUpdator(actInfoDto.getUpdator());
			actInfo.setUpdatorId(actInfoDto.getUpdatorId());
		}else{
			actInfoDto.setId(null);
			actInfo = new ActInfo();
			actInfoDto.setPriseCount(0);
			actInfoDto.setReplyCount(0);
			actInfoDto.setStatus(ActInfo.STATUS1);
			BeanUtils.copyProperties(actInfoDto, actInfo);
			actInfo.setIsRecommend(ActInfo.ISRECOMMEND0);
		}
		
		actInfo = this.getEntityDao().saveAndFlush(actInfo);
		actInfoDto.setId(actInfo.getId());
		//保存活动内容集合
		this.handleActInfoDto(actInfoDto);
		
		return actInfoDto;
	}
	
	@Transactional(value="transactionManager")
	private ActInfoDto handleActInfoDto(ActInfoDto actInfoDto){
		List<ActInfoList> actInfoList = actInfoDto.getActInfoList();
		if(CollectionUtils.isEmpty(actInfoList))return actInfoDto;
		for(ActInfoList ail : actInfoList){
			Byte type = ail.getType();
			//如果没有,则不做任何处理//页面的序号可能会产生属性值为空的对象
			if(null == type)continue;
			
			Long ailId = ail.getId();
			if(null != ailId && 0 != ailId){//修改
				ActInfoList sqlActInfoList = actInfoListDao.get(ailId);
				sqlActInfoList.setStatus(ail.getStatus());
				sqlActInfoList.setUpdator(actInfoDto.getUpdator());
				sqlActInfoList.setUpdateTime(actInfoDto.getUpdateTime());
				sqlActInfoList.setUpdatorId(actInfoDto.getUpdatorId());
				if(type.byteValue() == ActInfoList.TYPE2.byteValue()){//外链要修改外链地址及显示类型
					sqlActInfoList.setEndTime(actInfoDto.getEndTime());
					sqlActInfoList.setStartTime(actInfoDto.getStartTime());
					sqlActInfoList.setLinkUrl(ail.getLinkUrl());
					sqlActInfoList.setShowType(ail.getShowType());
					actInfoListDao.update(sqlActInfoList);
				}else if(type.byteValue() == ActInfoList.TYPE1.byteValue()){
					sqlActInfoList.setEndTime(actInfoDto.getEndTime());
					sqlActInfoList.setStartTime(actInfoDto.getStartTime());
				}else{//直接修改状态
					actInfoListDao.update(sqlActInfoList);
				}
			}else{//新增
				ail.setActInfoId(actInfoDto.getId());
				ail.setAppId(actInfoDto.getAppId());
				ail.setCreateTime(actInfoDto.getCreateTime());
				ail.setCreator(actInfoDto.getCreator());
				ail.setCreatorId(actInfoDto.getCreatorId());
				ail.setStartTime(actInfoDto.getStartTime());
				ail.setEndTime(actInfoDto.getEndTime());
				ail.setUpdateTime(actInfoDto.getUpdateTime());
				ail.setUpdator(actInfoDto.getUpdator());
				ail.setUpdatorId(actInfoDto.getUpdatorId());
				actInfoListDao.save(ail);
			}
		}
		return actInfoDto;
	}
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    @Override
    @Transactional(value="transactionManager")
    public void updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids){
        List<Long> idList = Arrays.asList(ids);
        this.getEntityDao().updateSortNo(sortNo, updator, updateUserId, new Date(), idList);
    }

	@Override
	public ActInfoDto findByActTest(Long actInfoListId) {
		return actInfoDao.findByActTest(actInfoListId);
	}

	@Override
	public ActInfoDto findByActInfoListId(Long actInfoListId) {
		return actInfoDao.findByActInfoListId(actInfoListId);
	}
	
	/**
     * Title:推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param ActInfo
     * @return
     */
	@Override
    @Transactional(value="transactionManager")
    public void recommend(ActInfo act){
	    if(null!=act.getAppId()){
	        //将其他已经推荐到首页的数据修改为未推荐
	        this.getEntityDao().cancelRecommend(ActInfo.ISRECOMMEND0, act.getUpdator(), act.getUpdatorId(), new Date(), act.getAppId(), ActInfo.ISRECOMMEND1);
	        //设置推荐到首页
	        this.getEntityDao().recommend(ActInfo.ISRECOMMEND1, act.getUpdator(), act.getUpdatorId(), new Date(), act.getRecommendImageUrl(),act.getId());
	    }
    }

	/**
     * Title:取消推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param updator
     * @param updateUserId
     * @param ids
     * @return
     */
	@Override
    @Transactional(value="transactionManager")
    public void cancelRecommend(String updator, Long updateUserId, Long id) {
        this.getEntityDao().cancelRecommend(ActInfo.ISRECOMMEND0,updator, updateUserId, new Date(), id);
    }
    /**
     * Title:数据导出
     * <p>Description:</p>
     * @author FangHuiLin on 2016年12月7日
     * @param classfyId
     * @return
     */
	@Override
	@Transactional(value="transactionManager")
	public List<ActExportDto> queryExportList(Long classfyId) {
		return actInfoListDao.actExportList(classfyId);
	}
}
