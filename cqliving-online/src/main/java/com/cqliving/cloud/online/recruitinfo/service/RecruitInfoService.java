package com.cqliving.cloud.online.recruitinfo.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.tool.common.Response;

/**
 * 人才招聘表 Service
 * Date: 2016-10-11 14:08:19
 * @author Code Generator
 */
public interface RecruitInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<RecruitInfo>> queryForPage(PageInfo<RecruitInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<RecruitInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(RecruitInfo domain);
	/** @author Code Generator *****end*****/
	public Response<Void> save(RecruitInfo domain,String[] urls);
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public Response<Void> updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);
    
	/**
	 * <p>Description: 职位列表</p>
	 * @author Tangtao on 2016年10月12日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	Response<ScrollPage<RecruitInfo>> queryForScrollPage(ScrollPage<RecruitInfo> scrollPage, Map<String, Object> conditions);
	
}
