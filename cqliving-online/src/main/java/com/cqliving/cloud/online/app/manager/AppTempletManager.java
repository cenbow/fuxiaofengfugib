package com.cqliving.cloud.online.app.manager;

import com.cqliving.cloud.online.app.domain.AppTemplet;
import com.cqliving.framework.common.service.EntityService;

/**
 * 客户端模板表 Manager
 * Date: 2016-05-03 20:01:37
 * @author Code Generator
 */
public interface AppTempletManager extends EntityService<AppTemplet> {
    public void deleteTemplet(Long[] ids)throws Exception;
    public void saveTemplet(AppTemplet domain)throws Exception;
    //复制公共模板到某个app
    public void copyConTemplet(Long appId);
}
