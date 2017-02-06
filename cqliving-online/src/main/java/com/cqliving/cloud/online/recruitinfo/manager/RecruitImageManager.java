package com.cqliving.cloud.online.recruitinfo.manager;

import java.util.List;

import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 人才招聘图片表 Manager
 * Date: 2016-10-12 10:09:13
 * @author Code Generator
 */
public interface RecruitImageManager extends EntityService<RecruitImage> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
     * 通过招聘信息查找图片
     * @param recruitInfoId
     * @return
     */
	public List<RecruitImage> getByRecruitInfoId(Long recruitInfoId);
}
