package com.cqliving.cloud.online.consult.manager;

import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.service.EntityService;

import java.util.Map;

import com.cqliving.cloud.online.consult.domain.ConsultInfo;
import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;

/**
 * 工商联咨询表 Manager
 * Date: 2016-11-29 14:58:28
 * @author Code Generator
 */
public interface ConsultInfoManager extends EntityService<ConsultInfo> {
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
	 * 我要咨询列表(滚动分页)
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月29日下午3:20:16
	 */
	public ScrollPage<ConsultInfoDto> queryConsultScrollPage(ScrollPage<ConsultInfoDto> page, Map<String, Object> conditions, String sessionId, String token);
	
	/**
	 * 保存
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月29日下午4:24:49
	 */
	public void saveConsultInfo(Long appId, String type, String content, String enterpriseName,
            String linkmanName, String linkmanPhone, String token, String sessionId,String captcha);
	
	/**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日下午2:38:57
     */
	public void reply(ConsultInfo consult);
}
