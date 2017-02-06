package com.cqliving.cloud.online.recruitinfo.manager;

import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

import java.util.Map;

import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;

/**
 * 人才招聘表 Manager
 * Date: 2016-10-11 14:08:19
 * @author Code Generator
 */
public interface RecruitInfoManager extends EntityService<RecruitInfo> {
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
	 * 保存
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年10月12日上午10:37:10
	 */
	public void save(RecruitInfo domain, String[] urls);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public void updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);

	/**
	 * <p>Description: 职位列表</p>
	 * @author Tangtao on 2016年10月12日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	ScrollPage<RecruitInfo> queryForScrollPage(ScrollPage<RecruitInfo> scrollPage, Map<String, Object> conditions);
	
}