package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.dao.ActVoteClassifyDao;
import com.cqliving.cloud.online.act.dao.ActVoteDao;
import com.cqliving.cloud.online.act.dao.ActVoteItemDao;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.cloud.online.act.domain.ActVoteItem;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.act.dto.ActVoteItemDto;
import com.cqliving.cloud.online.act.manager.ActVoteManager;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.cloud.online.info.manager.InfoFileManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.StringUtil;

@Service("actVoteManager")
public class ActVoteManagerImpl extends EntityServiceImpl<ActVote, ActVoteDao> implements ActVoteManager {
	
	@Autowired
	ActInfoListDao actInfoListDao;
	@Autowired
	ActVoteClassifyDao actVoteClassifyDao;
	@Autowired
	ActVoteItemDao actVoteItemDao;
	@Autowired
	InfoFileManager infoFileManager;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActVote.STATUS99, idList);
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
		return this.getEntityDao().updateStatus(ActVote.STATUS99, idList);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.ActVoteManager#saveActVoteDto(com.cqliving.cloud.online.act.dto.ActVoteDto)
	 */
	@Override
	@Transactional(value="transactionManager")
	public ActVoteDto saveActVoteDto(ActVoteDto actVoteDto,InfoFile infoFile) {
		
		actVoteDto = this.handleTimes(actVoteDto);
		
		ActVote actVote = new ActVote();
		BeanUtils.copyProperties(actVoteDto, actVote);
		
		List<ActVoteClassify> sqlActVoteClassify = null;
		if(null != actVote.getId() && 0 != actVote.getId()){
			ActVote sqlVote = this.get(actVote.getId());
			actVote.setStatus(sqlVote.getStatus());
			actVote.setCreateTime(sqlVote.getCreateTime());
			actVote.setCreator(sqlVote.getCreator());
			actVote.setCreatorId(sqlVote.getCreatorId());
			actVote.setStatus(sqlVote.getStatus());
			sqlActVoteClassify = actVoteClassifyDao.findByActVoteId(actVote.getId());
		}else{
			actVote.setStatus(ActVote.STATUS3);
		}
		
		//视频资源表
		if(null != infoFile){
			infoFile.setAppId(actVoteDto.getAppId());
			infoFile.setCreateTime(actVote.getCreateTime());
			infoFile.setCreator(actVote.getCreator());
			infoFile.setCreatorId(actVote.getCreatorId());
		}
		
		//活动集合表
		ActInfoList actInfoList = this.handle(actVoteDto);
		actVote.setActInfoListId(actInfoList.getId());
		
		actVote = this.getEntityDao().saveAndFlush(actVote);
		
		List<ActVoteClassifyDto> actVoteClassifyDtos = actVoteDto.getActVoteClassifyDtos();
		
		if(CollectionUtils.isEmpty(actVoteClassifyDtos))
			throw new BusinessException(ErrorCodes.FAILURE,"投票选项不能为空");
		
		//比较投票分类
		this.compareVoteClassify(sqlActVoteClassify, actVoteClassifyDtos);
		
		for(ActVoteClassifyDto voteClassifyDto : actVoteClassifyDtos){
			
			List<ActVoteItemDto> actVoteItems = voteClassifyDto.getActVoteItems();
			if(CollectionUtils.isEmpty(actVoteItems))
				throw new BusinessException(ErrorCodes.FAILURE,"投票选项不能为空");
			
			voteClassifyDto.setActInfoId(actVote.getActInfoId());
			voteClassifyDto.setActInfoListId(actInfoList.getId());
			voteClassifyDto.setActVoteId(actVote.getId());
			if(null == voteClassifyDto.getId() || 0== voteClassifyDto.getId()){
				voteClassifyDto.setCreateTime(actVoteDto.getUpdateTime());
			}
			//处理投票分类
			ActVoteClassify actVoteClassify = this.handle(voteClassifyDto);
			//比较投票项
			List<ActVoteItem> sqlActVoteItems = actVoteItemDao.findByClassifyId(actVoteClassify.getId());
			this.compareVoteItem(sqlActVoteItems, actVoteItems);
			
			for(ActVoteItemDto voteItem : actVoteItems){
				if(null == voteItem.getId() || 0 == voteItem.getId()){
					voteItem.setCreateTime(actVoteDto.getUpdateTime());
					voteItem.setStatus(ActVoteItem.STATUS3);
					voteItem.setActValue(null == voteItem.getActValue() ? 0 : voteItem.getActValue());
				}
				if(null != infoFile && !StringUtil.isEmpty(voteItem.getQiniuKey())){
					InfoFile newInfoFile = new InfoFile();
					BeanUtils.copyProperties(infoFile,newInfoFile);
					newInfoFile.setId(null);
					newInfoFile.setQiniuHash(voteItem.getQiniuHash());
					newInfoFile.setQiniuKey(voteItem.getQiniuKey());
					newInfoFile.setOriginalName(voteItem.getOriginalName());
					newInfoFile = this.saveInfoFile(newInfoFile);
					if(null != newInfoFile){
						voteItem.setInfoFileId(newInfoFile.getId());
					}
				}
				voteItem.setActVoteClassifyId(actVoteClassify.getId());
				ActVoteItem actVoteItem = new ActVoteItem();
				BeanUtils.copyProperties(voteItem, actVoteItem);
				actVoteItemDao.saveAndFlush(actVoteItem);
			}
		}
		actVoteDto.setId(actVote.getId());
		return actVoteDto;
	}
	
	@Transactional(value="transactionManager")
	private void compareVoteClassify(List<ActVoteClassify> sqlActVoteClassify,List<ActVoteClassifyDto> actVoteClassifyDtos){
		
		if(CollectionUtils.isEmpty(actVoteClassifyDtos) || CollectionUtils.isEmpty(sqlActVoteClassify)){
			return;
		}
		//如果页面actVoteClassifyDtos的ID在数据库中不存在，则将数据库中的删掉
		Iterator<ActVoteClassify> sqlIter = sqlActVoteClassify.iterator();
		while(sqlIter.hasNext()){
			ActVoteClassify sqlclassify = sqlIter.next();
			boolean isDelete = true;
			for(ActVoteClassifyDto dto : actVoteClassifyDtos){
				if(null != dto && null!=dto.getId() && sqlclassify.getId().longValue() == dto.getId().longValue()){//
					isDelete = false;
					break;
				}
			}
			if(isDelete){
				sqlclassify.setStatus(ActVoteClassify.STATUS99);
				actVoteClassifyDao.update(sqlclassify);
			}
		}
	}
	
	@Transactional(value="transactionManager")
	private void compareVoteItem(List<ActVoteItem> sqlActVoteItems,List<ActVoteItemDto> actVoteItems){
		
		if(CollectionUtils.isEmpty(sqlActVoteItems) || CollectionUtils.isEmpty(actVoteItems)){
			return;
		}
		//如果页面actVoteItems的ID在数据库中不存在，则将数据库中的删掉
		Iterator<ActVoteItem> sqlItor = sqlActVoteItems.iterator();
        while(sqlItor.hasNext()){
        	ActVoteItem sqlItem = sqlItor.next();
        	boolean isDelete = true;
			for(ActVoteItemDto dto : actVoteItems){
				if(null != dto.getId() && sqlItem.getId().longValue() == dto.getId().longValue()){//
					isDelete = false;
					break;
				}
			}
			if(isDelete){
				sqlItem.setStatus(ActVoteItem.STATUS99);
				actVoteItemDao.update(sqlItem);
			}
        }		
	}
	
	@Transactional(value="transactionManager")
	private InfoFile saveInfoFile(InfoFile infoFile){
		
		infoFile.setType(InfoFile.TYPE1);
		
		return infoFileManager.saveInfoFile(infoFile);
	}
	
	private ActVoteDto handleTimes(ActVoteDto actVoteDto){
		
		
		if(null == actVoteDto)return null;
		
		int times = -1;
		
		Integer rateTimes = actVoteDto.getLimitRateTimes();
		Integer ruleTimes = actVoteDto.getLimitRuleTimes();
		Integer shareTimes = actVoteDto.getLimitShareTimes();
		Integer singleTimes = actVoteDto.getLimitSingleTimes();
		Integer shareAddTimes = actVoteDto.getShareAddTimes();
		if(null == rateTimes || 0 == rateTimes.intValue()){
			actVoteDto.setLimitRateTimes(times);
		}
		if(null == ruleTimes || 0 == ruleTimes.intValue()){
			actVoteDto.setLimitRuleTimes(times);
		}
		if(null == shareTimes || 0 == shareTimes.intValue()){
			actVoteDto.setLimitShareTimes(times);
		}
		if(null == singleTimes || 0 == singleTimes.intValue()){
			actVoteDto.setLimitSingleTimes(times);
		}
		if(null == shareAddTimes || 0 == shareAddTimes.intValue()){
			actVoteDto.setShareAddTimes(times);
		}
		return actVoteDto;
	}
	
	
	@Transactional(value="transactionManager")
	private ActInfoList handle(ActVoteDto actVoteDto){
		
		Long actInfoListId = actVoteDto.getActInfoListId();
		ActInfoList actInfoList = new ActInfoList();
		
		actInfoList.setCreateTime(actVoteDto.getCreateTime());
		actInfoList.setCreator(actVoteDto.getCreator());
		actInfoList.setCreatorId(actVoteDto.getCreatorId());
		actInfoList.setStatus(ActInfoList.STATUS1);
		actInfoList.setType(ActInfoList.TYPE3);
		actInfoList.setAppId(actVoteDto.getAppId());
		actInfoList.setActInfoId(actVoteDto.getActInfoId());
		if(null != actInfoListId && 0 != actInfoListId){
			actInfoList = actInfoListDao.get(actInfoListId);
		}
		actInfoList.setShowType(ActInfoList.TYPE3);
		actInfoList.setUpdateTime(actVoteDto.getUpdateTime());
		actInfoList.setUpdator(actVoteDto.getUpdator());
		actInfoList.setUpdatorId(actVoteDto.getUpdatorId());
		actInfoList.setStartTime(actVoteDto.getStartTime());
		actInfoList.setEndTime(actVoteDto.getEndTime());
		
		return actInfoListDao.saveAndFlush(actInfoList);
		
	}
	
	@Transactional(value="transactionManager")
	private ActVoteClassify handle(ActVoteClassifyDto actVoteClassifyDto){
		
		ActVoteClassify actVoteClassify = new ActVoteClassify();
		BeanUtils.copyProperties(actVoteClassifyDto, actVoteClassify);
		actVoteClassify.setStatus(ActVoteClassify.STATUS3);
		if(null == actVoteClassify.getIsImageVote()){
			actVoteClassify.setIsImageVote(ActVoteClassify.ISIMAGEVOTE0);
		}
		return actVoteClassifyDao.saveAndFlush(actVoteClassify);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.ActVoteManager#findDetailById(java.lang.Long)
	 */
	@Override
	public ActVoteDto findDetailById(Long id) {
		
		return this.getEntityDao().findDetailById(id);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.manager.ActVoteManager#findByActInfoListId(java.lang.Long)
	 */
	@Override
	public ActVote findByActInfoListId(Long actInfoListId) {
		
		return this.getEntityDao().findByActInfoListId(actInfoListId);
	}
}
