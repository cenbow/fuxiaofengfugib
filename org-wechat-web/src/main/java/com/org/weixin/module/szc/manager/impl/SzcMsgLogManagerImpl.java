package com.org.weixin.module.szc.manager.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wechat.framework.service.EntityServiceImpl;

import com.cqliving.framework.utils.Dates;
import com.org.weixin.module.szc.dao.SzcMsgLogDao;
import com.org.weixin.module.szc.domain.SzcMsgLog;
import com.org.weixin.module.szc.manager.SzcMsgLogManager;
import com.org.weixin.module.szc.sms.vo.RequestData;
import com.org.weixin.module.szc.sms.vo.SmsRequest;
import com.org.weixin.module.szc.sms.vo.SmsResponse;
import com.org.weixin.util.JsonUtil;


@Service("szcMsgLogManager")
public class SzcMsgLogManagerImpl extends EntityServiceImpl<SzcMsgLog, SzcMsgLogDao> implements SzcMsgLogManager {

	/* (non-Javadoc)
	 * @see com.org.weixin.module.szc.manager.SzcMsgLogManager#saveSzcMsgLog(com.org.weixin.module.jywth.common.sms.vo.SMSResponse)
	 */
	@Override
	@Transactional
	public void saveSzcMsgLog(SmsResponse response,RequestData requestData,SmsRequest sMSRequest,String code) {

      if(null == response){
    	  return;
      }
      SzcMsgLog szcMsgLog = new SzcMsgLog();
      szcMsgLog.setCreateTime(Dates.now());
      szcMsgLog.setErrno(response.getMeta().getErrno());
      szcMsgLog.setMsgCode(code);
      szcMsgLog.setPhone(requestData.getPhone());
      String requestParams = JsonUtil.toJSONString(requestData);
      sMSRequest.setData(null);
      requestParams += JsonUtil.toJSONString(sMSRequest);
      szcMsgLog.setRemoteParams(requestParams);
      szcMsgLog.setRemoteResp(response.getMeta().getMsg());
      this.getEntityDao().saveAndFlush(szcMsgLog);
	}
}
