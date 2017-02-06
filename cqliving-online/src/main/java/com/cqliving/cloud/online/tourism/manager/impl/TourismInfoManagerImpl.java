package com.cqliving.cloud.online.tourism.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.tourism.dao.TourismImageDao;
import com.cqliving.cloud.online.tourism.dao.TourismInfoDao;
import com.cqliving.cloud.online.tourism.dao.TourismSpecialDao;
import com.cqliving.cloud.online.tourism.domain.TourismImage;
import com.cqliving.cloud.online.tourism.domain.TourismInfo;
import com.cqliving.cloud.online.tourism.dto.TourismInfoDto;
import com.cqliving.cloud.online.tourism.manager.TourismInfoManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("tourismInfoManager")
public class TourismInfoManagerImpl extends EntityServiceImpl<TourismInfo, TourismInfoDao> implements TourismInfoManager {
	
	@Autowired
	private TourismImageDao tourismImageDao;
	@Autowired
	private TourismSpecialDao tourismSpecialDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		//删除专题关联
		tourismSpecialDao.deleteByTourism(idList);
		return this.getEntityDao().updateStatus(TourismInfo.STATUS99, idList);
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
	public PageInfo<TourismInfoDto> queryDtoForPage(PageInfo<TourismInfoDto> pageInfo, Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class, value = "transactionManager")
	public void modifySortNo(Long id, Integer sortNo) {
		if (sortNo == null) {
			sortNo = Integer.MAX_VALUE;
		}
		getEntityDao().modifySortNo(id, sortNo);
	}

	@Override
	public ScrollPage<TourismInfoDto> queryForScrollPage(ScrollPage<TourismInfoDto> scrollPage, double lat, double lng, Long appId, String regionCode, Byte type, String tourismName) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_app_id", appId);
		map.put("EQ_is_show", TourismInfo.ISSHOW1);	
		map.put("EQ_region_code", regionCode);
		map.put("EQ_type", type);
		map.put("LIKE_name", tourismName);
		map.put("EQ_status", TourismInfo.STATUS3);
		return getEntityDao().queryForScrollPageByDistance(scrollPage, map, lat, lng);
	}

	@Override
	@Transactional(rollbackFor=Throwable.class, value="transactionManager")
	public void saveByAdmin(TourismInfo tourismInfo, String images, Long userId, String userName) {
		Date date = new Date();
        boolean isUpdate = tourismInfo.getId() != null;
        tourismInfo = save(tourismInfo);
        if(isUpdate){//是修改就要删除旧图片
        	tourismImageDao.deleteByTourismId(tourismInfo.getId());
        }
		if(StringUtils.isNotBlank(images)){
			List<TourismImage> list = Lists.newArrayList();
			TourismImage tourismImage;
			String imgs[] = images.split(",");
			for(String str : imgs){
				tourismImage = new TourismImage();
				tourismImage.setAppId(tourismInfo.getAppId());
				tourismImage.setTourismId(tourismInfo.getId());
				tourismImage.setUrl(str);
				tourismImage.setSortNo(0);
				tourismImage.setCreator(userName);
				tourismImage.setCreatorId(userId);
				tourismImage.setUpdator(userName);
				tourismImage.setUpdatorId(userId);
				tourismImage.setStatus(TourismImage.STATUS3);
				tourismImage.setCreateTime(date);
				tourismImage.setUpdateTime(date);
				list.add(tourismImage);
			}
			if(list.size() > 0){
				tourismImageDao.saves(list);	
			}
		}
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
    public void updateSortNo(Integer sortNo, String updator, Long updateUserId, Long... ids) {
        List<Long> idList = Arrays.asList(ids);
        this.getEntityDao().updateSortNo(sortNo, updator, updateUserId, new Date(), idList);
    }
}