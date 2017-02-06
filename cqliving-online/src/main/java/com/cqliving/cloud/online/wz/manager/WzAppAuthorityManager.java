package com.cqliving.cloud.online.wz.manager;

import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问政权限设置值表 Manager
 * Date: 2016-05-10 09:46:20
 * @author Code Generator
 */
public interface WzAppAuthorityManager extends EntityService<WzAppAuthority> {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param id
     * @return
     */
    WzAppAuthority getByAuthorityId(Long appId, Long id);
}
