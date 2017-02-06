package com.cqliving.cloud.online.recruitinfo.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.recruitinfo.dao.RecruitImageDao;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.cloud.online.recruitinfo.manager.RecruitImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("recruitImageManager")
public class RecruitImageManagerImpl extends EntityServiceImpl<RecruitImage, RecruitImageDao> implements RecruitImageManager {
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(RecruitImage.STATUS99, idList);
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
     * 通过招聘信息查找图片
     * @param recruitInfoId
     * @return
     */
    @Override
    public List<RecruitImage> getByRecruitInfoId(Long recruitInfoId) {
        return this.getEntityDao().findByRecruitInfoId(RecruitImage.STATUS3,recruitInfoId);
    }
}
