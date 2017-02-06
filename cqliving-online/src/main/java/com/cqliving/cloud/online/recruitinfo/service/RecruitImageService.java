package com.cqliving.cloud.online.recruitinfo.service;

import java.util.List;
import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.tool.common.Response;

/**
 * 人才招聘图片表 Service
 * Date: 2016-10-12 10:09:13
 * @author Code Generator
 */
public interface RecruitImageService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<RecruitImage>> queryForPage(PageInfo<RecruitImage> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<RecruitImage> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(RecruitImage domain);
	/** @author Code Generator *****end*****/
	public Response<List<RecruitImage>> getByRecruitInfoId(Long recruitInfoId);
	
}
