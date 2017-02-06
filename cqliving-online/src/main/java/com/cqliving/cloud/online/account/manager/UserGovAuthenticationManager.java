package com.cqliving.cloud.online.account.manager;

import com.cqliving.cloud.online.account.domain.UserGovAuthentication;
import com.cqliving.framework.common.service.EntityService;

/**
 * 用户行政审批认证表 Manager
 * Date: 2016-12-29 09:17:02
 * @author Code Generator
 */
public interface UserGovAuthenticationManager extends EntityService<UserGovAuthentication> {
    
    /**
     * 获取认证信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午9:44:17
     */
    public UserGovAuthentication get(String token, String sessionId, Long appId);
    
    /**
     * 保存认证
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午10:49:04
     */
    public void save(String token, String sessionId, Long appId,String name,String idCard,Byte sex,String nation,String phone,String captcha);
}
