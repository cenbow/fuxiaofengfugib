package com.cqliving.cloud.online.wz.manager;

import java.util.List;

import com.cqliving.cloud.online.wz.domain.WzUser;
import com.cqliving.framework.common.service.EntityService;

/**
 * 子帐号列表 Manager
 * Date: 2016-05-17 15:18:12
 * @author Code Generator
 */
public interface WzUserManager extends EntityService<WzUser> {
    
    /**
     * Title:根据AppId获得子帐号
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月19日
     * @param appId
     * @return
     */
    List<WzUser> getByAppId(Long appId);
}
