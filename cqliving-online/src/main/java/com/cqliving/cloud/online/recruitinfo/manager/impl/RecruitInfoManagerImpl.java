package com.cqliving.cloud.online.recruitinfo.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.recruitinfo.dao.RecruitImageDao;
import com.cqliving.cloud.online.recruitinfo.dao.RecruitInfoDao;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.cloud.online.recruitinfo.manager.RecruitInfoManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("recruitInfoManager")
public class RecruitInfoManagerImpl extends EntityServiceImpl<RecruitInfo, RecruitInfoDao> implements RecruitInfoManager {
    
  @Autowired
  private RecruitImageDao RecruitImageDao;
    
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(RecruitInfo.STATUS99, idList);
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
    public void save(RecruitInfo domain, String[] urls) {
        if(null!=domain.getId()){
            this.update(domain);
            RecruitImageDao.updateStatusByRecruitInfoId(RecruitImage.STATUS99, domain.getId());
            saveImages(domain, urls);
        }else{
            this.save(domain);
            saveImages(domain, urls);
        }
    }
    
    /**
     * 保存图片
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年10月12日上午11:14:58
     */
    private void saveImages(RecruitInfo domain, String[] urls){
        if(null!=urls&&urls.length>0){
            RecruitImage image;
            List<RecruitImage> imageList = new ArrayList<RecruitImage>();
            int i = 0;
            for (String url : urls) {
                image = new RecruitImage();
                image.setUrl(url);
                image.setAppId(domain.getAppId());
                image.setRecruitInfoId(domain.getId());
                image.setSortNo(++i);
                image.setStatus(RecruitImage.STATUS3);
                image.setCreatorId(domain.getUpdatorId());
                image.setUpdatorId(domain.getUpdatorId());
                image.setCreator(domain.getUpdator());
                image.setUpdator(domain.getUpdator());
                image.setCreateTime(domain.getUpdateTime());
                image.setUpdateTime(domain.getUpdateTime());
                imageList.add(image);
            }
            RecruitImageDao.saves(imageList);
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

	@Override
	public ScrollPage<RecruitInfo> queryForScrollPage(ScrollPage<RecruitInfo> scrollPage, Map<String, Object> conditions) {
		return getEntityDao().queryForScrollPage(scrollPage, conditions);
	}
	
}