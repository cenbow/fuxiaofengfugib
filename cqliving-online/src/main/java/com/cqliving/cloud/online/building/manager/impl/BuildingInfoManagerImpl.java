package com.cqliving.cloud.online.building.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.building.dao.BuildingImageDao;
import com.cqliving.cloud.online.building.dao.BuildingInfoDao;
import com.cqliving.cloud.online.building.domain.BuildingImage;
import com.cqliving.cloud.online.building.domain.BuildingInfo;
import com.cqliving.cloud.online.building.dto.BuildingInfoDto;
import com.cqliving.cloud.online.building.manager.BuildingInfoManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;

@Service("buildingInfoManager")
public class BuildingInfoManagerImpl extends EntityServiceImpl<BuildingInfo, BuildingInfoDao> implements BuildingInfoManager {
	
	@Autowired
	private BuildingImageDao buildingImageDao;
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(BuildingInfo.STATUS99, idList);
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
	
	@Override
	@Transactional(value="transactionManager")
	public BuildingInfo save(BuildingInfo buildingInfo, String[] images, String[] descType, String[] descArea, Long userId, String userName) {
		Date date = new Date();
		boolean isUpdate = buildingInfo.getId() != null;//		true :是修改
		if(!isUpdate){//是新增
			buildingInfo.setCreateTime(date);
			buildingInfo.setCreator(userName);
			buildingInfo.setCreatorId(userId);
		}
		buildingInfo.setUpdateTime(date);
		buildingInfo.setUpdator(userName);
		buildingInfo.setUpdatorId(userId);
		buildingInfo.setAverageHigh(buildingInfo.getAverageHigh() == null ? 0 : buildingInfo.getAverageHigh());
		buildingInfo.setAverageLow(buildingInfo.getAverageLow() == null ? 0 : buildingInfo.getAverageLow());
		buildingInfo.setSortNo(buildingInfo.getSortNo() == null ? Integer.MAX_VALUE : buildingInfo.getSortNo());
		buildingInfo.setStatus(buildingInfo.getStatus() == null ? BuildingInfo.STATUS1 : buildingInfo.getStatus());
		buildingInfo.setAveragePrice(buildingInfo.getAveragePrice() != null ? buildingInfo.getAveragePrice() * 100 : 0);
		buildingInfo.setHouseType(StringUtils.isNotBlank(buildingInfo.getHouseType()) ? ("," + buildingInfo.getHouseType() + ",") : null);
		
		//楼盘地标处理
		buildingInfo.setLandmark(this.getMark(buildingInfo.getLandmark()));
		//标签处理
		buildingInfo.setBuildingLabel(this.getMark(buildingInfo.getBuildingLabel()));
		//列表图处理
		String listImageUrls = buildingInfo.getListImageUrl();
		if(StringUtils.isNotBlank(listImageUrls)){
			String listImages[] = listImageUrls.split(",");
			for(String str : listImages){
				if(StringUtils.isNotBlank(str)){//列表图片保存轮播图中的第一张
					buildingInfo.setListImageUrl(str);
					break;
				}
			}
		}
		//保存基本信息
		buildingInfo = this.getEntityDao().save(buildingInfo);
		
		if(isUpdate){
			//删除旧的轮播图和户型图。
			buildingImageDao.deleteByBuildingId(buildingInfo.getId());
		}
		//轮播图
		List<BuildingImage> imageList = Lists.newArrayList();
		BuildingImage buildingImage;
		if(StringUtils.isNotBlank(listImageUrls)){
			String listImages[] = listImageUrls.split(",");
			for(String str : listImages){
				if(StringUtils.isNotBlank(str)){
					buildingImage = new BuildingImage();
					buildingImage.setAppId(buildingInfo.getAppId());
					buildingImage.setBuildingInfoId(buildingInfo.getId());
					buildingImage.setCreateTime(date);
					buildingImage.setCreator(userName);
					buildingImage.setCreatorId(userId);
					buildingImage.setUrl(str);
					buildingImage.setSortNo(Integer.MAX_VALUE);
					buildingImage.setStatus(BuildingImage.STATUS3);
					buildingImage.setType(BuildingImage.TYPE1);
					buildingImage.setUpdateTime(date);
					buildingImage.setUpdator(userName);
					buildingImage.setUpdatorId(userId);
					imageList.add(buildingImage);
				}
			}
		}
		//户型图处理
		if(images != null){
			for(int i = 0; i < images.length; i ++){
				if(StringUtils.isNotBlank(images[i])){
					buildingImage = new BuildingImage();
					buildingImage.setAppId(buildingInfo.getAppId());
					buildingImage.setBuildingInfoId(buildingInfo.getId());
					buildingImage.setCreateTime(date);
					buildingImage.setCreator(userName);
					buildingImage.setCreatorId(userId);
					buildingImage.setDescArea(descArea == null || descArea.length == 0 ? null : descArea[i]);
					buildingImage.setDescType(descType == null || descType.length == 0 ? null : descType[i]);
					buildingImage.setUrl(images[i]);
					buildingImage.setSortNo(Integer.MAX_VALUE);
					buildingImage.setStatus(BuildingImage.STATUS3);
					buildingImage.setType(BuildingImage.TYPE2);
					buildingImage.setUpdateTime(date);
					buildingImage.setUpdator(userName);
					buildingImage.setUpdatorId(userId);
					imageList.add(buildingImage);
				}
			}
		}
		if(imageList.size() > 0){//保存图片
			buildingImageDao.saves(imageList);
		}
		return buildingInfo;
	}
	
	/**
	 * Title: 地标、标签的处理
	 * <p>Description:新增和修改用</p>
	 * @author DeweiLi on 2016年10月12日
	 * @param str
	 * @return 英文半角逗号开始和结束，中间用英文半角逗号隔开
	 */
	private String getMark(String str){
		String mark = "";
		if(StringUtils.isBlank(str))
			return mark;
		String marks[] = str.split(",");
		for(String s : marks){
			if(!"".equals(s))
				mark += "," + s;
		}
		if(!"".equals(mark))
			mark += ",";
		return mark;
	}

	@Override
	@Transactional(value="transactionManager")
	public int modifySortNo(Long id, Integer sortNo) {
		if(sortNo == null)
			sortNo = Integer.MAX_VALUE;
		return this.getEntityDao().modifySortNo(id, sortNo);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.building.manager.BuildingInfoManager#queryScrollPage(com.cqliving.framework.common.dao.support.ScrollPage, java.util.Map)
	 */
	@Override
	public ScrollPage<BuildingInfo> queryScrollPage(ScrollPage<BuildingInfo> scrollPage, Map<String, Object> conditions) {
		
		return this.getEntityDao().queryScrollPage(scrollPage, conditions);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.building.manager.BuildingInfoManager#buildingDetail(java.lang.Long)
	 */
	@Override
	public BuildingInfoDto buildingDetail(Long buildingId) {
		
		if(null == buildingId){
			return null;
		}
		BuildingInfo info = this.get(buildingId);
		if(null == info){
			return null;
		}
		BuildingInfoDto dto = new BuildingInfoDto();
		BeanUtils.copyProperties(info, dto);;
		dto.setBuildingImg(buildingImageDao.findByBuildingId(buildingId));
		return dto;
	}
}
