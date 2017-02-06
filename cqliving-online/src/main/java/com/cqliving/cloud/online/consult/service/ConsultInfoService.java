package com.cqliving.cloud.online.consult.service;

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.cloud.online.consult.domain.ConsultInfo;
import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;
import com.cqliving.tool.common.Response;

/**
 * 工商联咨询表 Service
 * Date: 2016-11-29 14:58:28
 * @author Code Generator
 */
public interface ConsultInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ConsultInfo>> queryForPage(PageInfo<ConsultInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ConsultInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ConsultInfo domain);
	/** @author Code Generator *****end*****/
	/**
     * 我要咨询列表(滚动分页)
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午3:11:59
     */
	public Response<ScrollPage<ConsultInfoDto>> queryConsultScrollPage(ScrollPage<ConsultInfoDto> page, Map<String, Object> conditions, String sessionId, String token) ;
	
	/**
	 * 保存问题咨询
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月29日下午4:17:02
	 */
	public Response<Boolean> saveConsultInfo(Long appId, String type, String content, String enterpriseName, String linkmanName, String linkmanPhone, String token, String sessionId,String captcha);
	
	/**
	 * 回复
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年12月1日下午2:38:57
	 */
	public Response<Void> reply(ConsultInfo domain);
}
