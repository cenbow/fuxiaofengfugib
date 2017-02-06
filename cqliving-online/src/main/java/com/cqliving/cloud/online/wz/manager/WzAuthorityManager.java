package com.cqliving.cloud.online.wz.manager;

import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问政权限配置表 Manager
 * Date: 2016-05-09 17:26:06
 * @author Code Generator
 */
public interface WzAuthorityManager extends EntityService<WzAuthority> {
    
    /**
     * Title:根据名称返回一个
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月11日
     * @param name
     * @return
     */
    WzAuthority getByNameOne(String name);
}
